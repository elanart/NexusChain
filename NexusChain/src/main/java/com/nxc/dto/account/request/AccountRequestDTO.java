package com.nxc.dto.account.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDTO {
    @NotNull(message = "{account.username.notnullMsg}")
    @Size(min = 1, max = 50, message = "{account.username.sizeMsg}")
    @Pattern(regexp = "^[a-zA-Z0-9.]{3,}$", message = "{account.username.patternMsg}")
    private String username;
    @NotNull(message = "{account.password.notnullMsg}")
    private String password;
}
