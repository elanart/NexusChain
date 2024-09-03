package com.nxc.dto.carrier.respone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarrierResponseDTO {
    private Long id;
    private String name;
    private BigDecimal rating;
    private String cooperationTerms;
}
