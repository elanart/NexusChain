package com.nxc.repository;

import com.nxc.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserRepository {
    User findById(Long id);
    List<User> findUser(Map<String, String> params);
    void saveOrUpdate(User user);
    List<User> findAll();
}
