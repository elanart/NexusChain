package com.nxc.service.impl;

import com.nxc.dto.user.request.UserRequestDTO;
import com.nxc.dto.user.request.UserUpdateRequestDTO;
import com.nxc.dto.user.response.UserResponseDTO;
import com.nxc.enums.RoleEnum;
import com.nxc.pojo.Account;
import com.nxc.pojo.Carrier;
import com.nxc.pojo.Supplier;
import com.nxc.pojo.User;
import com.nxc.repository.CarrierRepository;
import com.nxc.repository.SupplierRepository;
import com.nxc.repository.UserRepository;
import com.nxc.service.AccountService;
import com.nxc.service.CloudinaryService;
import com.nxc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountService accountService;
    private final SupplierRepository supplierRepository;
    private final CarrierRepository carrierRepository;
    private final CloudinaryService cloudinaryService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        MultipartFile avatar = userRequestDTO.getAvatar();
        String avatarUrl = null;
        if (avatar != null && !avatar.isEmpty()) {
            avatarUrl = this.cloudinaryService.uploadImage(avatar);
        }

        User user = User.builder()
                .fullName(userRequestDTO.getFullName())
                .address(userRequestDTO.getAddress())
                .phone(userRequestDTO.getPhone())
                .email(userRequestDTO.getEmail())
                .avatar(avatarUrl)
                .role(userRequestDTO.getRole())
                .isConfirm(false)
                .isDeleted(false)
                .createdDate(new Date())
                .build();

        String hashedPassword = passwordEncoder.encode(userRequestDTO.getPassword());

        Account account = Account.builder()
                .username(userRequestDTO.getUsername())
                .password(hashedPassword)
                .user(user)
                .build();

        user.setAccount(account);

        if(user.getRole() == RoleEnum.ROLE_ADMIN) {
            user.setIsConfirm(true);
        }

        this.accountService.saveOrUpdate(account);
        this.userRepository.saveOrUpdate(user);

        return UserResponseDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .address(user.getAddress())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .isConfirm(user.getIsConfirm())
                .isDeleted(user.getIsDeleted())
                .avatar(user.getAvatar())
                .build();
    }

    @Override
    public void confirmUser(Long userId) {
        User user = this.userRepository.findById(userId);
        if (user != null && !user.getIsConfirm()) {
            user.setIsConfirm(true);
            this.userRepository.saveOrUpdate(user);
        }
    }

    @Override
    @Transactional
    public void updateUser(String username, UserUpdateRequestDTO userUpdateRequestDTO) {
        Account account = this.accountService.findByUsername(username);
        User user = account.getUser();
        if (user != null && user.getIsConfirm()) {

            if (userUpdateRequestDTO.getFullName() != null) {
                user.setFullName(userUpdateRequestDTO.getFullName());
            }

            if (userUpdateRequestDTO.getAddress() != null) {
                user.setAddress(userUpdateRequestDTO.getAddress());
            }

            if (userUpdateRequestDTO.getPhone() != null) {
                user.setPhone(userUpdateRequestDTO.getPhone());
            }

            user.setUpdatedDate(new Date());

            if (user.getRole() == RoleEnum.ROLE_SUPPLIER){
                Supplier supplier = Supplier.builder()
                        .paymentTerms(userUpdateRequestDTO.getPaymentTerms())
                        .build();

                this.supplierRepository.saveSupplier(supplier);

            } else if (user.getRole() == RoleEnum.ROLE_CARRIER){
                Carrier carrier = Carrier.builder()
                        .cooperationTerms(userUpdateRequestDTO.getCooperationTerms())
                        .build();

                this.carrierRepository.saveCarrier(carrier);
            }

            this.userRepository.saveOrUpdate(user);
        }
    }

    @Override
    public UserResponseDTO getUserDetails(Long userId) {
        User user = this.userRepository.findById(userId);
        if (user != null) {
            return UserResponseDTO.builder()
                    .id(user.getId())
                    .fullName(user.getFullName())
                    .address(user.getAddress())
                    .phone(user.getPhone())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .isConfirm(user.getIsConfirm())
                    .isDeleted(user.getIsDeleted())
                    .avatar(user.getAvatar())
                    .build();
        }
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = this.userRepository.findById(userId);
        if (user != null) {
            user.setIsDeleted(true);
            this.userRepository.saveOrUpdate(user);
        }
    }

    @Override
    public List<User> findUser(Map<String, String> params) {
        return this.userRepository.findUser(params);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.accountService.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        User user = account.getUser();

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(), account.getPassword(), authorities);
    }
}
