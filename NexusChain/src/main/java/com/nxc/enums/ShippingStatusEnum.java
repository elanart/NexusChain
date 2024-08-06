package com.nxc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShippingStatusEnum {
    SHIPPED,
    IN_TRANSIT,
    DELIVERED,
    RETURNED;
}
