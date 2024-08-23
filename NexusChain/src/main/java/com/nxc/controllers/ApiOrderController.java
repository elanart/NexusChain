package com.nxc.controllers;

import com.nxc.dto.order.request.OrderDetailRequestDTO;
import com.nxc.dto.order.request.OrderRequestDTO;
import com.nxc.dto.order.response.OrderDetailResponseDTO;
import com.nxc.dto.order.response.OrderResponseDTO;
import com.nxc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin
public class ApiOrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO orderResponseDTO = this.orderService.addOrder(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDTO);
    }

    @PostMapping("/{orderId}/details")
    public ResponseEntity<OrderDetailResponseDTO> addOrderDetail(@PathVariable Long orderId, @RequestBody OrderDetailRequestDTO orderDetailRequestDTO) {
        OrderDetailResponseDTO orderDetailResponseDTO = this.orderService.addOrderDetail(orderId, orderDetailRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailResponseDTO);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequestDTO orderRequestDTO) {
        orderRequestDTO.setId(orderId);
        OrderResponseDTO updatedOrder = this.orderService.updateOrder(orderRequestDTO);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        this.orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long orderId) {
        OrderResponseDTO orderResponseDTO = this.orderService.getOrder(orderId);
        return ResponseEntity.ok(orderResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = this.orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{orderId}/confirm")
    public ResponseEntity<OrderResponseDTO> confirmOrder(@PathVariable Long orderId, @RequestBody Long supplierId) {
        OrderResponseDTO orderResponseDTO = this.orderService.confirmOrder(orderId, supplierId);
        return ResponseEntity.ok(orderResponseDTO);
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable Long orderId, @RequestBody Long userId) {
        if(this.orderService.cancelOrder(orderId, userId)) {
            return new ResponseEntity<>("Order cancelled successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Order cancelled failed", HttpStatus.BAD_REQUEST);
    }
}



