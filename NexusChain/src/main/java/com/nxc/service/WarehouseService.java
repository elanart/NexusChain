/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nxc.service;

import com.nxc.dto.warehouse.response.WarehouseResponseDTO;
import com.nxc.dto.warehouse.resquest.WarehouseResquestDTO;
import com.nxc.pojo.Warehouse;

/**
 *
 * @author tuann
 */
public interface WarehouseService {
    Warehouse findById(String id);
    void saveOrUpdate(Warehouse warehouse);

    public WarehouseResponseDTO saveOrUpdate(WarehouseResquestDTO warehouseResquestDTO);
}
