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
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDTO {
    private Long id;

    private String fullName;

    private String address;

    private String phone;

    private MultipartFile avatar;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "{user.email.patternMsg}")
    private String email;

    private RoleEnum role;

    private String paymentTerms;

    private String cooperationTerms;

}
