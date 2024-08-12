package com.nxc.repository;

import com.nxc.pojo.Account;

import java.util.List;

public interface AccountRepository {
    Account findByUsername(String username);
    void saveOrUpdate(Account account);
    List<Account> getAccounts();
    Account findById(Long id);
}
