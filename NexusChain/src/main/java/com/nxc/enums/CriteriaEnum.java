package com.nxc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CriteriaEnum {
    Quality("Chất lượng"),
    Timely_Delivery("Giao hàng đúng hạn"),
    Cost("Giá cả");

    private final String displayName;
}
