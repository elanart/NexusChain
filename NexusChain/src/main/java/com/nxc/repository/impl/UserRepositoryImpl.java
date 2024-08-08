package com.nxc.repository.impl;

import com.nxc.pojo.User;
import com.nxc.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void save(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        session.save(user);
    }
}
