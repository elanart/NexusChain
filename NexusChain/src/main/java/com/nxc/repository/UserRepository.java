package com.nxc.repository;

import com.nxc.enums.RoleEnum;
import com.nxc.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserRepository {
    List<User> getUsers(Map<String, String> params);
    User saveOrUpdate(User user);
    void delete(User user);
    User getUserByUsername(String username);
    List<User> findByRole(RoleEnum role);
    User findById(Long id);
}
