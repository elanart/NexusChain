/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.service.impl;

import com.nxc.dto.warehouse.response.WarehouseResponseDTO;
import com.nxc.dto.warehouse.resquest.WarehouseResquestDTO;
import com.nxc.pojo.Warehouse;
import com.nxc.repository.WarehouseRepository;
import com.nxc.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author tuann
 */
@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    @Override
    public Warehouse findById(String id) {
        return this.warehouseRepository.findById(id);
    }

    @Override
    public void saveOrUpdate(Warehouse warehouse) {
        this.warehouseRepository.saveOrUpdate(warehouse);
    }

    @Override
    public WarehouseResponseDTO saveOrUpdate(WarehouseResquestDTO warehouseResquestDTO) {
        // Chuyển đổi từ DTO sang Entity
        Warehouse warehouse = new Warehouse();
        warehouse.setLocation(warehouseResquestDTO.getLocation());
        warehouse.setCapacity(warehouseResquestDTO.getCapacity());
        warehouse.setCost(warehouseResquestDTO.getCost());

        // Lưu hoặc cập nhật Warehouse entity
        this.warehouseRepository.saveOrUpdate(warehouse);

        // Chuyển đổi từ Entity sang ResponseDTO
        WarehouseResponseDTO responseDTO = new WarehouseResponseDTO();
        responseDTO.setId(warehouse.getId());
        responseDTO.setLocation(warehouse.getLocation());
        responseDTO.setCapacity(warehouse.getCapacity());
        responseDTO.setCost(warehouse.getCost());

        return responseDTO;
    }
}

