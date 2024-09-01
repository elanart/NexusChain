/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.dto.warehouse.response;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author tuann
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseResponseDTO {
    private Long id;
    private Integer capacity;
    private BigDecimal cost;
    private Date createdDate;
    private Boolean isActive;
    private String location;
    private Date updatedDate;
}
