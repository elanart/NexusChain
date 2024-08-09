package com.nxc.dto.user.response;

import com.nxc.dto.account.request.AccountRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponse {
    private UserResponse user;
    private AccountRequest account;
}
