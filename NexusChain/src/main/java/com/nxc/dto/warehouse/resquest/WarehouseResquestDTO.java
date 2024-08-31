/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.dto.warehouse.resquest;

import java.math.BigDecimal;
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
public class WarehouseResquestDTO {
    private Long id;
    private Integer capacity;
    private BigDecimal cost;
    private String location;
}
