package com.nxc.dto.carrier.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarrierRequestDTO {
    private Long userId;
    private BigDecimal rating;
    private String cooperationTerms;
}
