package com.nxc.controllers;

import com.nxc.dto.order.response.OrderResponseDTO;
import com.nxc.service.OrderService;
import com.nxc.service.UserService;
import com.nxc.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@CrossOrigin
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final OrderService orderService;
    private final WarehouseService warehouseService;

    @GetMapping
    public String homePage() {
        return "index";
    }

    @GetMapping("/accounts")
    public String listAccounts(Model model, @RequestParam Map<String, String> params){
        model.addAttribute("users", this.userService.findUser(params));

        return "account";
    }

    @PostMapping("/accounts")
    public String confirmUser(@RequestParam("id") Long id) {
        this.userService.confirmUser(id);
        return "redirect:/admin/accounts";
    }

    @PostMapping("/accounts/delete")
    public String deleteUser(@RequestParam("userId") Long userId) {
        this.userService.deleteUser(userId);
        return "redirect:/admin/accounts";
    }

    @GetMapping("/orders")
    public String viewOrders(@RequestParam Map<String, String> params, Model model) {
        List<OrderResponseDTO> orders = orderService.getAllOrders(params);
        model.addAttribute("orders", orders);

        for (OrderResponseDTO order : orders) {
            String userFullName = orderService.getUserFullName(order.getUserId());
            model.addAttribute("userFullName" + order.getId(), userFullName);
        }

        return "order";
    }

    @GetMapping("/orders/{orderId}")
    public String viewOrderDetail(@PathVariable Long orderId, Model model) {
        OrderResponseDTO order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        return "orderDetails";
    }

    @PostMapping("/orders/{orderId}/confirm")
    public String confirmOrder(@PathVariable Long orderId) {
        Long adminUserId = 1L;
        orderService.confirmOrder(orderId, adminUserId);
        return "redirect:/admin/orders";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        Long adminUserId = 1L;
        orderService.cancelOrder(orderId, adminUserId);
        return "redirect:/admin/orders";
    }
    
    @GetMapping("/warehouse")
    public String viewWarehouse(Model model) {
        model.addAttribute("warehouse", this.warehouseService.getAllWarehouses());
        return "warehouse";
    }
}
