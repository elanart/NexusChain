package com.nxc.dto.shipment.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentRequestDTO {
    @NotNull(message = "Carrier ID là bắt buộc")
    private Long carrierId;
    @NotNull(message = "Warehouse ID là bắt buộc")
    private Long warehouseId;
    @NotNull(message = "Ngày giao hàng là bắt buộc")
    private Date shipmentDate;
    private String trackingNumber;
    private Date expectedDelivery;
    @NotBlank(message = "Điểm đến là bắt buộc")
    @Size(min = 3, max = 100, message = "Điểm đến phải có từ 3 đến 100 ký tự")
    private String destination;
    @NotNull(message = "Chi phí là bắt buộc")
    @DecimalMin(value = "0.0", inclusive = false, message = "Chi phí phải lớn hơn 0")
    private BigDecimal cost;
}
