package com.nxc.service;

import com.nxc.dto.user.request.UserRequestDTO;
import com.nxc.dto.user.response.UserResponseDTO;
import com.nxc.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);
    void confirmUser(Long userId);
    void updateUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUserDetails(Long userId);
    void requestDeleteUser(Long userId);
    List<User> findUser(Map<String, String> params);
}
