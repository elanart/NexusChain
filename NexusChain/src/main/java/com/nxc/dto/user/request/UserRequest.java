package com.nxc.dto.user.request;

import com.nxc.dto.account.request.AccountRequest;
import com.nxc.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String fullName;
    private String address;
    private String phone;
    private String avatar;
    private String email;
    private RoleEnum role;
    private AccountRequest account;
    private MultipartFile avatarFile;
}