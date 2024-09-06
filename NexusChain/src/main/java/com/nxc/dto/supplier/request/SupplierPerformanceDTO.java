package com.nxc.dto.supplier.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierPerformanceDTO {
    private Long supplierId;
    private String supplierName;
    private Double averageQualityRating;
    private Double averageDeliveryRating;
    private Double averageCostRating;
}
