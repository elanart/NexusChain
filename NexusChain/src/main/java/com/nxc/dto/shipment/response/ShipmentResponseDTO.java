package com.nxc.dto.shipment.response;

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
public class ShipmentResponseDTO {
    private Long id;
    private Date shipmentDate;
    private String trackingNumber;
    private Date expectedDelivery;
    private BigDecimal cost;
    private String status;
    private String carrierName;
    private String destination;
    private String warehouseLocation;
}
