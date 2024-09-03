package com.nxc.dto.supplier.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierPerformanceRequestDTO {
    private Long supplierId;
    private Date startDate;
    private Date endDate;
}
