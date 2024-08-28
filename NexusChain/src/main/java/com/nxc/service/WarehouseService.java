package com.nxc.service;

import com.nxc.pojo.Warehouse;

import java.util.List;

public interface WarehouseService {
    void addWarehouse(Warehouse warehouse);
    Warehouse getWarehouse(Long warehouseId);
    List<Warehouse> getAllWarehouses();
    void updateWarehouse(Warehouse warehouse);
    void deleteWarehouse(Long warehouseId);
}
