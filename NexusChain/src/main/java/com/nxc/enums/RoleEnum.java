package com.nxc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {
    ROLE_ADMIN("Quản trị viên"),
    ROLE_DISTRIBUTOR("Đại lý"),
    ROLE_SUPPLIER("Nhà cung cấp"),
    ROLE_CARRIER("Đối tác vận chuyển");

    private final String displayName;
}
