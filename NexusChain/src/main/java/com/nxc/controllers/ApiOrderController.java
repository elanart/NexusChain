package com.nxc.controllers;

import com.nxc.dto.order.request.OrderDetailRequestDTO;
import com.nxc.dto.order.request.OrderRequestDTO;
import com.nxc.dto.order.response.OrderDetailResponseDTO;
import com.nxc.dto.order.response.OrderResponseDTO;
import com.nxc.pojo.Account;
import com.nxc.pojo.User;
import com.nxc.service.AccountService;
import com.nxc.service.OrderService;
import com.nxc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin
public class ApiOrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO, BindingResult bindingResult, Principal principal) throws java.nio.file.AccessDeniedException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        String username = principal.getName();
        Account account = this.accountService.findByUsername(username);
        User user = account.getUser();
        orderRequestDTO.setUserId(user.getId());

        OrderResponseDTO orderResponseDTO = this.orderService.addOrder(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDTO);
    }

    @PostMapping("/{orderId}/details")
    public ResponseEntity<OrderDetailResponseDTO> addOrderDetail(@PathVariable Long orderId, @RequestBody @Valid OrderDetailRequestDTO orderDetailRequestDTO) {
        OrderDetailResponseDTO orderDetailResponseDTO = this.orderService.addOrderDetail(orderId, orderDetailRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailResponseDTO);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long orderId, @RequestBody @Valid OrderRequestDTO orderRequestDTO) {
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
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders(@RequestParam Map<String, String> params) {
        List<OrderResponseDTO> orders = this.orderService.getAllOrders(params);
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{orderId}/confirm")
    public ResponseEntity<OrderResponseDTO> confirmOrder(@PathVariable Long orderId, Principal principal) {
        String username = principal.getName();
        Account account = this.accountService.findByUsername(username);
        User user = account.getUser();

        OrderResponseDTO orderResponseDTO = this.orderService.confirmOrder(orderId, user.getId());
        return ResponseEntity.ok(orderResponseDTO);
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId, Principal principal) {
        String username = principal.getName();
        Account account = this.accountService.findByUsername(username);
        User user = account.getUser();

        boolean isCancelled = this.orderService.cancelOrder(orderId, user.getId());
        if (isCancelled) {
            return ResponseEntity.ok("Order cancelled successfully.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order cancellation failed.");
    }

    // Exception handling for common errors
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalState(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
}