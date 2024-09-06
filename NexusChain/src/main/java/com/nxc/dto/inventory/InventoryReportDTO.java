package com.nxc.dto.inventory;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryReportDTO {
    private int totalItems;
    private int nearExpiryItems;
    private int expiredItems;
}
