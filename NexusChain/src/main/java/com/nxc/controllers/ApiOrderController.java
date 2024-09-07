package com.nxc.controllers;

import com.nxc.dto.order.request.OrderDetailRequestDTO;
import com.nxc.dto.order.request.OrderRequestDTO;
import com.nxc.dto.order.response.OrderDetailResponseDTO;
import com.nxc.dto.order.response.OrderResponseDTO;
import com.nxc.pojo.Account;
import com.nxc.pojo.User;
import com.nxc.service.AccountService;
import com.nxc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<OrderDetailResponseDTO> addOrderDetail(@PathVariable Long orderId,
                                                                 @RequestBody @Valid OrderDetailRequestDTO orderDetailRequestDTO,
                                                                 Principal principal) {
        String username = principal.getName();
        Account account = this.accountService.findByUsername(username);
        User user = account.getUser();
        OrderResponseDTO order = orderService.getOrder(orderId);

        if (!order.getUserId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }

        OrderDetailResponseDTO orderDetailResponseDTO = this.orderService.addOrderDetail(orderId, orderDetailRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailResponseDTO);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long orderId, @RequestBody @Valid OrderRequestDTO orderRequestDTO,
                                                        Principal principal) {
        String username = principal.getName();
        Account account = this.accountService.findByUsername(username);
        User user = account.getUser();
        OrderResponseDTO order = orderService.getOrder(orderId);
        if (!order.getUserId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }

        orderRequestDTO.setId(orderId);
        OrderResponseDTO updatedOrder = this.orderService.updateOrder(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId, Principal principal) {
        String username = principal.getName();
        Account account = this.accountService.findByUsername(username);
        User user = account.getUser();
        OrderResponseDTO order = orderService.getOrder(orderId);
        if (!order.getUserId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }

        this.orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long orderId, Principal principal) {
        String username = principal.getName();
        Account account = this.accountService.findByUsername(username);
        User user = account.getUser();
        OrderResponseDTO orderResponse = orderService.getOrder(orderId);

        if (!orderResponse.getUserId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getUserOrders(@RequestBody Map<String, String> params, Principal principal) {
        String username = principal.getName();
        Account account = this.accountService.findByUsername(username);
        User user = account.getUser();
        params.put("userId", user.getId().toString());

        List<OrderResponseDTO> orders = this.orderService.getAllOrders(params);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
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
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }
}