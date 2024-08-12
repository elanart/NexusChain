package com.nxc.service;

import com.nxc.dto.user.request.UserRequest;
import com.nxc.enums.RoleEnum;
import com.nxc.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {
    List<User> getUsers(Map<String, String> params);
    void saveOrUpdate(User user);
    void delete(User user);
    User getUserByUsername(String username);
    User createUserAndAccount(UserRequest userRequest);
    List<User> findByRole(RoleEnum role);
    void confirmUserAccount(Long userId);
    User findById(Long id);
}
