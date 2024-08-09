package com.nxc.repository;

import com.nxc.enums.RoleEnum;
import com.nxc.pojo.User;

import java.util.List;

public interface UserRepository {
    List<User> findByRole(RoleEnum role);
    User saveOrUpdate(User user);
    void delete(User user);
    User getUserByUsername(String username);
}
