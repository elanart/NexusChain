package com.nxc.service;

import com.nxc.dto.order.request.OrderDetailRequestDTO;
import com.nxc.dto.order.request.OrderRequestDTO;
import com.nxc.dto.order.response.OrderDetailResponseDTO;
import com.nxc.dto.order.response.OrderResponseDTO;
import com.nxc.dto.user.request.UserRequestDTO;
import com.nxc.pojo.Order;
import com.nxc.pojo.OrderDetail;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;

public interface OrderService {
    OrderResponseDTO addOrder(OrderRequestDTO orderRequest) throws AccessDeniedException;
    OrderResponseDTO confirmOrder(Long orderId, Long userId);
    boolean cancelOrder(Long orderId, Long userId);
    OrderResponseDTO updateOrder(OrderRequestDTO orderRequest);
    OrderDetailResponseDTO addOrderDetail(Long orderId, OrderDetailRequestDTO orderDetailRequest);
    void deleteOrder(Long orderId);
    OrderResponseDTO getOrder(Long orderId);
    List<OrderResponseDTO> getAllOrders(Map<String, String> params);
}
