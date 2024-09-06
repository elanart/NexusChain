package com.nxc.dto.supplier.request;

import com.nxc.enums.CriteriaEnum;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRatingRequestDTO {
    private Long supplierId;
    private CriteriaEnum criterion;
    private Double rating;
    private String comment;
}
