package com.nxc.repository;

import com.nxc.pojo.Account;

public interface AccountRepository {
    void saveAccount(Account account);
    Account findByUsername(String username);
}
