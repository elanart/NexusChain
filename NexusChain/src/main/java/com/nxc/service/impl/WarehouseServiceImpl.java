package com.nxc.service.impl;

import com.nxc.dto.warehouse.response.WarehouseResponseDTO;
import com.nxc.dto.warehouse.resquest.WarehouseResquestDTO;
import com.nxc.pojo.Warehouse;
import com.nxc.repository.WarehouseRepository;
import com.nxc.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    @Override
    public void addWarehouse(Warehouse warehouse) {
        this.warehouseRepository.saveOrUpdate(warehouse);
    }

    @Override
    public Warehouse getWarehouse(Long warehouseId) {
        return this.warehouseRepository.findById(warehouseId);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return this.warehouseRepository.findAll();
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        this.warehouseRepository.saveOrUpdate(warehouse);
    }

    @Override
    public void deleteWarehouse(Long warehouseId) {
        Warehouse warehouse = this.warehouseRepository.findById(warehouseId);
        this.warehouseRepository.delete(warehouse);
    }

    @Override
    public void saveOrUpdate(WarehouseResquestDTO warehouseResquestDTO) {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocation(warehouseResquestDTO.getLocation());
        warehouse.setCapacity(warehouseResquestDTO.getCapacity());
        warehouse.setCost(warehouseResquestDTO.getCost());
        this.warehouseRepository.saveOrUpdate(warehouse);
    }
}
