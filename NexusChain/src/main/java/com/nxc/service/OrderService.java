package com.nxc.service;

import com.nxc.dto.order.request.OrderDetailRequestDTO;
import com.nxc.dto.order.request.OrderRequestDTO;
import com.nxc.dto.order.response.OrderResponseDTO;
import com.nxc.dto.user.request.UserRequestDTO;
import com.nxc.pojo.Order;
import com.nxc.pojo.OrderDetail;

import java.util.List;

public interface OrderService {
    OrderResponseDTO addOrUpdateOrder(OrderRequestDTO orderRequest);
    void deleteOrder(Long orderId);
    OrderResponseDTO getOrder(Long orderId);
    List<OrderResponseDTO> getAllOrders();
}
