package com.nxc.dto.shipment.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentRequestDTO {
    private Long carrierId;
    private Long warehouseId;
    private Date shipmentDate;
    private String trackingNumber;
    private Date expectedDelivery;
    private String destination;
    private BigDecimal cost;
}
