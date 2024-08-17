package com.nxc.dto.user.request;

import com.nxc.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private Long id;

    @NotNull(message = "{account.username.notnullMsg}")
    @Size(min = 1, max = 50, message = "{account.username.sizeMsg}")
    @Pattern(regexp = "^[a-zA-Z0-9.]{3,}$", message = "{account.username.patternMsg}")
    private String username;

    @NotNull(message = "{account.password.notnullMsg}")
    private String password;

    @NotNull(message = "{user.fullName.notnullMsg}")
    private String fullName;

    private String address;

    private String phone;

    private MultipartFile avatar;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "{user.email.patternMsg}")
    private String email;

    private RoleEnum role;
}
