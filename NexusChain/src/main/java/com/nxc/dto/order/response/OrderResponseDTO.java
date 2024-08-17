package com.nxc.dto.order.response;

import com.nxc.enums.OrderTypeEnum;
import com.nxc.enums.ShippingStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private Date orderDate;
    private ShippingStatusEnum status;
    private OrderTypeEnum type;
    private Set<OrderDetailResponseDTO> orderDetails;
}
