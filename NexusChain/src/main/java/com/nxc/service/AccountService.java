package com.nxc.service;

import com.nxc.pojo.Account;
import com.nxc.pojo.User;

public interface AccountService {
    void saveAccount(Account account);
    Account findByUsername(String username);
    Account findById(Long id);
}
