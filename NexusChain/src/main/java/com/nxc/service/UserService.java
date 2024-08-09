package com.nxc.service;

import com.nxc.dto.user.request.UserRequest;
import com.nxc.enums.RoleEnum;
import com.nxc.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findByRole(RoleEnum role);
    void saveOrUpdate(User user);
    void delete(User user);
    User getUserByUsername(String username);
    User createUserAndAccount(UserRequest userRequest);
}
