package com.nxc.controllers;

import com.nxc.service.OrderService;
import com.nxc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/admin")
@CrossOrigin
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final OrderService orderService;

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
    public String listOrders(Model model, Map<String, String> params) {
        model.addAttribute("orders", this.orderService.getAllOrders(params));
        return "order";
    }
}
