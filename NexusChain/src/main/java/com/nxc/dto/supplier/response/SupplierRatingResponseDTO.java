package com.nxc.dto.supplier.response;

import com.nxc.enums.CriteriaEnum;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRatingResponseDTO {
    private Long id;
    private Long supplierId;
    private CriteriaEnum criterion;
    private Double rating;
    private String comment;
    private Date createdDate;
    private Date updatedDate;
}
