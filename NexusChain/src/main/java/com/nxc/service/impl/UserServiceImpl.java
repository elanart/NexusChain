package com.nxc.service.impl;

import com.nxc.pojo.Account;
import com.nxc.pojo.User;
import com.nxc.repository.AccountRepository;
import com.nxc.repository.UserRepository;
import com.nxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void save(User user) {
        Account account = user.getAccount();
        if (account != null) {
            account.setUser(user);
        }
        userRepository.save(user);
    }
}
