package com.nxc.service;

import com.nxc.pojo.Account;

public interface AccountService {
    void saveOrUpdate(Account account);
    Account findByUsername(String username);
    Account findById(Long id);
}
