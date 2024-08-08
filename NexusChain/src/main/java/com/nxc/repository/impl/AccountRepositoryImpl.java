package com.nxc.repository.impl;

import com.nxc.pojo.Account;
import com.nxc.repository.AccountRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void saveAccount(Account account) {
        Session session = this.factory.getObject().getCurrentSession();
        session.save(account);
    }

    @Override
    public Account findByUsername(String username) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(Account.class, username);
    }
}
