package com.nxc.service;

import com.nxc.pojo.Account;

public interface AccountService {
    Account findByUsername(String username);
    void saveOrUpdate(Account account);
}
