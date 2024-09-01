package com.nxc.service.impl;

import com.nxc.dto.order.request.OrderDetailRequestDTO;
import com.nxc.dto.order.request.OrderRequestDTO;
import com.nxc.dto.order.response.OrderDetailResponseDTO;
import com.nxc.dto.order.response.OrderResponseDTO;
import com.nxc.dto.user.request.UserRequestDTO;
import com.nxc.enums.OrderStatusEnum;
import com.nxc.enums.OrderTypeEnum;
import com.nxc.enums.RoleEnum;
import com.nxc.pojo.*;
import com.nxc.repository.*;
import com.nxc.service.InventoryService;
import com.nxc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;
    private final InventoryService inventoryService;

    @Override
    public OrderResponseDTO addOrder(OrderRequestDTO orderRequest) throws AccessDeniedException {
        User user = this.userRepository.findById(orderRequest.getUserId());
        if (user == null) {
            throw new EntityNotFoundException("Người dùng không tồn tại.");
        }

        Warehouse warehouse = this.warehouseRepository.findById(orderRequest.getWarehouseId());
        if (warehouse == null) {
            throw new EntityNotFoundException("Kho hàng không tồn tại.");
        }

        if ((user.getRole() == RoleEnum.ROLE_SUPPLIER && orderRequest.getType() == OrderTypeEnum.INBOUND) ||
                (user.getRole() == RoleEnum.ROLE_DISTRIBUTOR && orderRequest.getType() == OrderTypeEnum.OUTBOUND)) {

            Order order = Order.builder()
                    .type(orderRequest.getType())
                    .user(user)
                    .warehouse(warehouse)
                    .build();

            this.orderRepository.saveOrUpdate(order);
            return this.getOrderResponseDTO(order);
        } else {
            throw new AccessDeniedException("Người dùng không có quyền tạo đơn hàng với loại này.");
        }
    }

    @Override
    public OrderResponseDTO confirmOrder(Long orderId, Long userId) {
        Order order = this.orderRepository.findById(orderId);
        if (order == null) {
            throw new EntityNotFoundException("Đơn hàng không tồn tại.");
        }

        User user = this.userRepository.findById(userId);
        if (user == null) {
            throw new EntityNotFoundException("Người dùng không tồn tại.");
        }

        if ((order.getType() == OrderTypeEnum.INBOUND && (user.getRole() != RoleEnum.ROLE_SUPPLIER && user.getRole() != RoleEnum.ROLE_ADMIN)) ||
                (order.getType() == OrderTypeEnum.OUTBOUND && (user.getRole() != RoleEnum.ROLE_DISTRIBUTOR && user.getRole() != RoleEnum.ROLE_ADMIN))) {
            throw new IllegalArgumentException("Người dùng không có quyền xác nhận đơn hàng này.");
        }

        if (order.getStatus() != OrderStatusEnum.PENDING) {
            throw new IllegalStateException("Đơn hàng đã được xử lý!");
        }

        order.setIsConfirm(true);
        order.setStatus(OrderStatusEnum.COMPLETED);
        this.orderRepository.saveOrUpdate(order);

        return this.getOrderResponseDTO(order);
    }

    @Override
    public boolean cancelOrder(Long orderId, Long userId) {
        Order order = this.orderRepository.findById(orderId);
        if (order == null) {
            throw new EntityNotFoundException("Đơn hàng không tồn tại.");
        }

        User user = this.userRepository.findById(userId);
        if (user == null) {
            throw new EntityNotFoundException("Người dùng không tồn tại.");
        }

        if ((order.getType() == OrderTypeEnum.INBOUND && (user.getRole() != RoleEnum.ROLE_SUPPLIER && user.getRole() != RoleEnum.ROLE_ADMIN)) ||
                (order.getType() == OrderTypeEnum.OUTBOUND && (user.getRole() != RoleEnum.ROLE_DISTRIBUTOR && user.getRole() != RoleEnum.ROLE_ADMIN))) {
            throw new IllegalArgumentException("Người dùng không có quyền hủy đơn hàng này.");
        }

        inventoryService.updateInventoryById(order.getId());

        order.setStatus(OrderStatusEnum.CANCELLED);
        this.orderRepository.saveOrUpdate(order);
        return true;
    }

    @Override
    public OrderDetailResponseDTO addOrderDetail(Long orderId, OrderDetailRequestDTO orderDetailRequest) {
        Order order = this.orderRepository.findById(orderId);
        if (order == null) {
            throw new EntityNotFoundException("Đơn hàng không tồn tại.");
        }

        Product product = this.productRepository.findById(orderDetailRequest.getProductId());
        if (product == null) {
            throw new EntityNotFoundException("Sản phẩm không tồn tại.");
        }

        Warehouse warehouse = order.getWarehouse();
        if (warehouse == null) {
            throw new IllegalStateException("Đơn hàng không có thông tin về kho.");
        }

        if (order.getType() == OrderTypeEnum.INBOUND) {
            Inventory inventory = this.inventoryRepository.getByWarehouseId(warehouse.getId());
            int availableCapacity = warehouse.getCapacity() - inventory.getQuantity();

            if (orderDetailRequest.getQuantity() > availableCapacity) {
                throw new IllegalStateException("Không đủ sức chứa trong kho.");
            }
        } else if (order.getType() == OrderTypeEnum.OUTBOUND) {
            Inventory inventory = inventoryRepository.findByProductIdAndWarehouseId(product.getId(), warehouse.getId());
            if (inventory == null || inventory.getQuantity() < orderDetailRequest.getQuantity()) {
                throw new IllegalStateException("Không đủ hàng tồn kho.");
            }

            inventory.setQuantity(inventory.getQuantity() - orderDetailRequest.getQuantity());
            this.inventoryRepository.saveOrUpdate(inventory);
        }

        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .quantity(orderDetailRequest.getQuantity())
                .price(orderDetailRequest.getPrice())
                .build();

        this.orderDetailRepository.saveOrUpdate(orderDetail);

        return this.mapToOrderDetailResponseDTO(orderDetail);
    }

    @Override
    public OrderResponseDTO updateOrder(OrderRequestDTO orderRequest) {
        Order existingOrder = this.orderRepository.findById(orderRequest.getId());
        if (existingOrder == null) {
            throw new EntityNotFoundException("Đơn hàng không tồn tại.");
        }

        Warehouse warehouse = this.warehouseRepository.findById(orderRequest.getWarehouseId());
        if (warehouse == null) {
            throw new EntityNotFoundException("Kho hàng không tồn tại.");
        }

        existingOrder.setStatus(orderRequest.getStatus());
        existingOrder.setType(orderRequest.getType());
        existingOrder.setUser(this.userRepository.findById(orderRequest.getUserId()));
        existingOrder.setWarehouse(this.warehouseRepository.findById(orderRequest.getWarehouseId()));

        this.orderRepository.saveOrUpdate(existingOrder);

        return this.getOrderResponseDTO(existingOrder);
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = this.orderRepository.findById(orderId);
        if (order == null) {
            throw new EntityNotFoundException("Đơn hàng không tồn tại.");
        }

        this.orderRepository.delete(order);
    }

    @Override
    public OrderResponseDTO getOrder(Long orderId) {
        Order order = this.orderRepository.findById(orderId);
        if (order == null) {
            throw new EntityNotFoundException("Đơn hàng không tồn tại.");
        }

        return this.getOrderResponseDTO(order);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders(Map<String, String> params) {
        return this.orderRepository.findAll(params).stream()
                .map(this::getOrderResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void placeOrder(Order order) {
        orderRepository.saveOrUpdate(order);
        inventoryService.updateInventoryById(order.getId());
    }

    @Override
    public String getUserFullName(Long userId) {
        User user = userRepository.findById(userId);
        return user != null ? user.getFullName() : "Unknown";
    }

    private OrderResponseDTO getOrderResponseDTO(Order order) {
        Set<OrderDetailResponseDTO> orderDetailResponseDTOs = (order.getOrderDetails() != null)
                ? order.getOrderDetails().stream()
                .map(this::mapToOrderDetailResponseDTO)
                .collect(Collectors.toSet())
                : Collections.emptySet();

        return OrderResponseDTO.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .type(order.getType())
                .isConfirm(order.getIsConfirm())
                .warehouseId(order.getWarehouse().getId())
                .orderDetails(orderDetailResponseDTOs)
                .userId(order.getUser().getId())
                .userFullName(order.getUser().getFullName())
                .build();
    }

    private OrderDetailResponseDTO mapToOrderDetailResponseDTO(OrderDetail detail) {
        return OrderDetailResponseDTO.builder()
                .id(detail.getId())
                .productId(detail.getProduct().getId())
                .productName(detail.getProduct().getName())
                .quantity(detail.getQuantity())
                .price(detail.getPrice())
                .build();
    }
}
