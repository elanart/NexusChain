package com.nxc.service.impl;

import com.nxc.enums.RoleEnum;
import com.nxc.pojo.Account;
import com.nxc.pojo.User;
import com.nxc.repository.UserRepository;
import com.nxc.service.InitializerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InitializerServiceImpl implements InitializerService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createAdminUser() {
        if (userRepository.findByRole(RoleEnum.ROLE_ADMIN).isEmpty()) {
            User admin = new User();
            admin.setFullName("Quản trị viên");
            admin.setIsDeleted(false);
            admin.setEmail("admin@nexuschain.com");
            admin.setRole(RoleEnum.ROLE_ADMIN);

            String hashedPassword = this.passwordEncoder.encode("123456");

            Account adminAccount = new Account();
            adminAccount.setUsername("admin");
            adminAccount.setPassword(hashedPassword);
            adminAccount.setUser(admin);

            admin.setAccount(adminAccount);

            this.userRepository.saveOrUpdate(admin);
        }
    }
}
