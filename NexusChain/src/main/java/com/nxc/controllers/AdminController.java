package com.nxc.controllers;

import com.nxc.pojo.User;
import com.nxc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/accounts")
    public String listAccounts(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("users", this.userService.getUsers(params));
        return "account";
    }

    @PostMapping("/accounts")
    public String confirmAccount(@RequestParam("userId") Long userId) {
        this.userService.confirmUserAccount(userId);
        return "redirect:/admin/accounts";
    }
}
