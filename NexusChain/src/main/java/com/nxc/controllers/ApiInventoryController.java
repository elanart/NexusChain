package com.nxc.controllers;

import com.nxc.enums.RoleEnum;
import com.nxc.pojo.Account;
import com.nxc.pojo.User;
import com.nxc.service.AccountService;
import com.nxc.service.InventoryService;
import com.nxc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class ApiInventoryController {
    private final InventoryService inventoryService;
    private final AccountService accountService;

    @PostMapping("/update/{orderId}")
    public ResponseEntity<String> updateInventory(@PathVariable Long orderId, Principal principal) {
        String username = principal.getName();
        Account account = this.accountService.findByUsername(username);
        User user = account.getUser();

        if (user.getRole() == RoleEnum.ROLE_SUPPLIER || user.getRole() == RoleEnum.ROLE_DISTRIBUTOR) {
            inventoryService.updateInventoryById(orderId);
            return ResponseEntity.ok("Inventory updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to update inventory.");
        }
    }
}
