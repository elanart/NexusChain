package com.nxc.service;

import com.nxc.dto.user.request.UserRequestDTO;
import com.nxc.dto.user.request.UserUpdateRequestDTO;
import com.nxc.dto.user.response.UserResponseDTO;
import com.nxc.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);
    void confirmUser(Long userId);
    void updateUser(String username, UserUpdateRequestDTO userUpdateRequestDTO);
    UserResponseDTO getUserDetails(Long userId);
    void deleteUser(Long userId);
    List<User> findUser(Map<String, String> params);
}
