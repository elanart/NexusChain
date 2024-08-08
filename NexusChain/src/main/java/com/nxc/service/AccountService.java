package com.nxc.service;

import com.nxc.pojo.Account;

public interface AccountService {
    void saveAccount(Account account);
    Account findByUsername(String username);
}
