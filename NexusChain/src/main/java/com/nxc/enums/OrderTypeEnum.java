package com.nxc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderTypeEnum {
    INBOUND("Xuất kho"),
    OUTBOUND("Nhập kho");

    private final String displayName;
}
