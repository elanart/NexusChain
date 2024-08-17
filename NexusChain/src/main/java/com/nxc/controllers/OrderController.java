package com.nxc.controllers;

import com.nxc.dto.order.request.OrderRequestDTO;
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
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody @Valid OrderRequestDTO orderRequest, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        try {
            OrderResponseDTO responseDTO = orderService.addOrUpdateOrder(orderRequest);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Có lỗi xảy ra khi tạo đơn hàng.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId,
                                         @RequestBody @Valid OrderRequestDTO orderRequest, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        try {
            orderRequest.setId(orderId);
            OrderResponseDTO responseDTO = orderService.addOrUpdateOrder(orderRequest);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Đơn hàng không tồn tại.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Có lỗi xảy ra khi cập nhật đơn hàng.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        try {
            orderService.deleteOrder(orderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Đơn hàng không tồn tại.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Có lỗi xảy ra khi xóa đơn hàng.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Long orderId) {
        try {
            OrderResponseDTO orderResponse = orderService.getOrder(orderId);
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Đơn hàng không tồn tại.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}


