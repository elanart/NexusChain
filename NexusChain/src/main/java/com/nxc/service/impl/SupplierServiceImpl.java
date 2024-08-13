package com.nxc.service.impl;

import com.nxc.dto.account.request.AccountRequest;
import com.nxc.dto.supplier.request.SupplierRequest;
import com.nxc.dto.supplier.request.SupplierUpdateRequest;
import com.nxc.dto.supplier.response.SupplierResponse;
import com.nxc.dto.user.request.UserRequest;
import com.nxc.dto.user.response.UserResponse;
import com.nxc.pojo.Account;
import com.nxc.pojo.Supplier;
import com.nxc.pojo.User;
import com.nxc.repository.SupplierRepository;
import com.nxc.service.AccountService;
import com.nxc.service.CloudinaryService;
import com.nxc.service.SupplierService;
import com.nxc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final UserService userService;
    private final SupplierRepository supplierRepository;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryService cloudinaryService;

    @Override
    public SupplierResponse registerSupplier(SupplierRequest request) {
        UserRequest userRequest = request.getUser();

        String avatarUrl = userRequest.getAvatarFile() != null ? this.uploadAvatar(userRequest.getAvatarFile()) : userRequest.getAvatar();
        userRequest.setAvatar(avatarUrl);

        User user = this.userService.createUserAndAccount(userRequest);

        Supplier supplier = Supplier.builder()
                .paymentTerms(request.getPaymentTerms())
                .user(user)
                .build();

        this.supplierRepository.saveOrUpdate(supplier);

        return getSupplierResponse(user, supplier);
    }

    private String uploadAvatar(MultipartFile avatarFile) {
        try {
            return cloudinaryService.uploadImage(avatarFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload avatar", e);
        }
    }

    private SupplierResponse getSupplierResponse(User user, Supplier supplier) {

        UserResponse userResponse = UserResponse.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .address(user.getAddress())
                .phone(user.getPhone())
                .avatar(user.getAvatar())
                .isConfirmed(user.getIsConfirm())
                .createdDate(user.getCreatedDate())
                .build();

        return SupplierResponse.builder()
                .user(userResponse)
                .build();
    }

    @Override
    public SupplierResponse updateSupplier(Long id, SupplierUpdateRequest request) {
        User user = this.userService.findById(id);
        if (user == null) {
            throw new EntityNotFoundException("Không tìm thấy user");
        }

        if (!user.getIsConfirm()) {
            throw new IllegalStateException("Tài khoản chưa kích hoạt");
        }

        user.setFullName(request.getFullName());
        user.setAddress(request.getAddress());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        this.userService.saveOrUpdate(user);

        Supplier supplier = supplierRepository.findById(id);
        supplier.setPaymentTerms(request.getPaymentTerms());
        this.supplierRepository.saveOrUpdate(supplier);
        return getSupplierResponse(user, supplier);
    }

    @Override
    public void updateSupplierAccount(Long id, AccountRequest request) {
        User user = this.userService.findById(id);
        if (user == null) {
            throw new EntityNotFoundException("Không tìm thấy user");
        }

        if (!user.getIsConfirm()) {
            throw new IllegalStateException("Tài khoản chưa kích hoạt");
        }

        Account account = this.accountService.findById(id);
        account.setUsername(request.getUsername());

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        account.setPassword(hashedPassword);
        this.accountService.saveOrUpdate(account);
    }
}
