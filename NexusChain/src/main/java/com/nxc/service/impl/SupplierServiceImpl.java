package com.nxc.service.impl;

import com.nxc.dto.supplier.request.SupplierRegistrationRequest;
import com.nxc.dto.supplier.response.SupplierRegistrationResponse;
import com.nxc.dto.user.response.UserResponse;
import com.nxc.pojo.Supplier;
import com.nxc.pojo.User;
import com.nxc.repository.SupplierRepository;
import com.nxc.service.SupplierService;
import com.nxc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final UserService userService;
    private final SupplierRepository supplierRepository;

    @Override
    public SupplierRegistrationResponse registerSupplier(SupplierRegistrationRequest request) {
        User user = this.userService.createUserAndAccount(request.getUser());

        Supplier supplier = Supplier.builder()
                .paymentTerms(request.getPaymentTerms())
                .user(user)
                .build();

        this.supplierRepository.saveOrUpdate(supplier);

        UserResponse userResponse = UserResponse.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .address(user.getAddress())
                .phone(user.getPhone())
                .avatar(user.getAvatar())
                .isConfirmed(user.getIsConfirm())
                .createdDate(user.getCreatedDate())
                .build();

        return SupplierRegistrationResponse.builder()
                .user(userResponse)
                .build();
    }
}
