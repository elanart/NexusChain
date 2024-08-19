package com.nxc.dto.order.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponseDTO {
    private Long id;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private Long productId;
}
