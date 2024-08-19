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
import com.nxc.repository.OrderRepository;
import com.nxc.repository.ProductRepository;
import com.nxc.repository.UserRepository;
import com.nxc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrderResponseDTO addOrUpdateOrder(OrderRequestDTO orderRequest) {
        Order order = Order.builder()
                .orderDate(orderRequest.getOrderDate())
                .status(orderRequest.getStatus())
                .type(orderRequest.getType())
                .build();

        Set<OrderDetail> orderDetails = orderRequest.getOrderDetails().stream()
                .map(orderDetailRequestDTO -> createOrderDetail(orderDetailRequestDTO, order))
                .collect(Collectors.toSet());

        order.setOrderDetails(orderDetails);

        this.orderRepository.saveOrUpdate(order);

        return getOrderResponseDTO(order);
    }

    private OrderDetail createOrderDetail(OrderDetailRequestDTO dto, Order order) {
        Product product = this.productRepository.findById(dto.getProductId());

        return OrderDetail.builder()
                .product(product)
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .order(order)
                .build();
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = this.orderRepository.findById(orderId);
        this.orderRepository.delete(order);
    }

    @Override
    public OrderResponseDTO getOrder(Long orderId) {
        Order order = this.orderRepository.findById(orderId);
        return getOrderResponseDTO(order);
    }

    private OrderResponseDTO getOrderResponseDTO(Order order) {
        Set<OrderDetailResponseDTO> orderDetailResponseDTOs = order.getOrderDetails().stream()
                .map(this::mapToOrderDetailResponseDTO)
                .collect(Collectors.toSet());

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