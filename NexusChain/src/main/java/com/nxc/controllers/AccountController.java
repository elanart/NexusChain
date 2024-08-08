package com.nxc.controllers;

import com.nxc.pojo.Account;
import com.nxc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        accountService.saveAccount(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Account> getAccountByUsername(@PathVariable String username) {
        Account account = accountService.findByUsername(username);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
