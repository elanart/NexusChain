package com.nxc.service.impl;

import com.nxc.pojo.Account;
import com.nxc.repository.AccountRepository;
import com.nxc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void saveAccount(Account account) {
        this.accountRepository.saveOrUpdate(account);
    }

    @Override
    public Account findByUsername(String username) {
        return this.accountRepository.findByUsername(username);
    }

    @Override
    public Account findById(Long id) {
        return this.accountRepository.findById(id);
    }
}
