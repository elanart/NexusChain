package com.nxc.repository;

import com.nxc.pojo.Account;

public interface AccountRepository {
    Account findByUsername(String username);
    void saveOrUpdate(Account account);
}
