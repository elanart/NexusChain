package com.nxc.service.impl;

import com.nxc.dto.user.request.UserRequest;
import com.nxc.enums.RoleEnum;
import com.nxc.pojo.Account;
import com.nxc.pojo.User;
import com.nxc.repository.AccountRepository;
import com.nxc.repository.UserRepository;
import com.nxc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid Username!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                u.getAccount().getUsername(), u.getAccount().getPassword(), authorities);
    }

    @Override
    public List<User> findByRole(RoleEnum role) {
        return this.userRepository.findByRole(role);
    }

    @Override
    public void saveOrUpdate(User user) {
        this.userRepository.saveOrUpdate(user);
    }

    @Override
    public void delete(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.getUserByUsername(username);
    }

    @Override
    public User createUserAndAccount(UserRequest userRequest) {
        User user = User.builder()
                .fullName(userRequest.getFullName())
                .address(userRequest.getAddress())
                .phone(userRequest.getPhone())
                .avatar(userRequest.getAvatar())
                .email(userRequest.getEmail())
                .createdDate(new Date())
                .role(userRequest.getRole())
                .build();

        String hashedPassword = passwordEncoder.encode(userRequest.getAccount().getPassword());

        Account account = Account.builder()
                .username(userRequest.getAccount().getUsername())
                .password(hashedPassword)
                .user(user)
                .build();

        user.setAccount(account);
        this.accountRepository.saveOrUpdate(account);
        return this.userRepository.saveOrUpdate(user);
    }
}

