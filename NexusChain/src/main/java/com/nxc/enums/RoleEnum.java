package com.nxc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {
    ROLE_ADMIN("Quản trị viên"),
    ROLE_CUSTOMER("Khách hàng"),
    ROLE_SUPPLIER("Nhà cung cấp"),
    ROLE_CARRIER("Đối tác vận chuyển");

    private final String displayName;
}
