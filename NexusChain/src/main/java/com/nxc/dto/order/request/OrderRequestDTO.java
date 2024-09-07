package com.nxc.dto.order.request;

import com.nxc.enums.OrderStatusEnum;
import com.nxc.enums.OrderTypeEnum;
import com.nxc.enums.ShippingStatusEnum;
import com.nxc.pojo.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private Long id;

    private OrderStatusEnum status;

    private OrderTypeEnum type;

    @NotNull(message = "ID kho không được để trống")
    private Long warehouseId;

    private Long userId;
}
