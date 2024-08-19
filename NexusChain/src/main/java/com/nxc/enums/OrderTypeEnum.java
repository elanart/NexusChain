package com.nxc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderTypeEnum {
    INBOUND("Nhập kho"),
    OUTBOUND("Xuất kho");

    private final String displayName;
}
