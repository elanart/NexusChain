package com.nxc.service.impl;

import com.nxc.pojo.Account;
import com.nxc.repository.AccountRepository;
import com.nxc.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account findByUsername(String username) {
        return this.accountRepository.findByUsername(username);
    }

    @Override
    public void saveOrUpdate(Account account) {
        this.accountRepository.saveOrUpdate(account);
    }
}
