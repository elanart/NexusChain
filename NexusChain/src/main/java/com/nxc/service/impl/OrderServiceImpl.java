package com.nxc.service.impl;

import com.nxc.dto.order.request.OrderDetailRequestDTO;
import com.nxc.dto.order.request.OrderRequestDTO;
import com.nxc.dto.order.response.OrderDetailResponseDTO;
import com.nxc.dto.order.response.OrderResponseDTO;
import com.nxc.dto.user.request.UserRequestDTO;
import com.nxc.enums.RoleEnum;
import com.nxc.pojo.Order;
import com.nxc.pojo.OrderDetail;
import com.nxc.pojo.Product;
import com.nxc.pojo.User;
import com.nxc.repository.OrderDetailRepository;
import com.nxc.repository.OrderRepository;
import com.nxc.repository.ProductRepository;
import com.nxc.repository.UserRepository;
import com.nxc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderResponseDTO addOrder(OrderRequestDTO orderRequest) {
        User user = this.userRepository.findById(orderRequest.getUserId());
        if (user == null) {
            throw new EntityNotFoundException("Người dùng không tồn tại.");
        }

        Order order = Order.builder()
                .orderDate(orderRequest.getOrderDate())
                .status(orderRequest.getStatus())
                .type(orderRequest.getType())
                .user(user)
                .build();

        this.orderRepository.saveOrUpdate(order);
        return getOrderResponseDTO(order);
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

        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .quantity(orderDetailRequest.getQuantity())
                .price(orderDetailRequest.getPrice())
                .build();

        this.orderDetailRepository.saveOrUpdate(orderDetail);

        return mapToOrderDetailResponseDTO(orderDetail);
    }

    @Override
    public OrderResponseDTO updateOrder(OrderRequestDTO orderRequest) {
        Order existingOrder = this.orderRepository.findById(orderRequest.getId());
        if (existingOrder == null) {
            throw new EntityNotFoundException("Đơn hàng không tồn tại.");
        }

        existingOrder.setOrderDate(orderRequest.getOrderDate());
        existingOrder.setStatus(orderRequest.getStatus());
        existingOrder.setType(orderRequest.getType());
        existingOrder.setUser(this.userRepository.findById(orderRequest.getUserId()));

        this.orderRepository.saveOrUpdate(existingOrder);

        return getOrderResponseDTO(existingOrder);
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

        return getOrderResponseDTO(order);
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
                .orderDetails(orderDetailResponseDTOs)
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

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::getOrderResponseDTO)
                .collect(Collectors.toList());
    }
}
