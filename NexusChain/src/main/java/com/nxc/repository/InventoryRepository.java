package com.nxc.repository;

import com.nxc.pojo.Inventory;

import java.util.List;

public interface InventoryRepository {
    void saveOrUpdate(Inventory inventory);
    Inventory findById(Long id);
    void delete(Inventory inventory);
    List<Inventory> findAll();
    Inventory getByWarehouseId(Long warehouseId);
    Inventory findByProductIdAndWarehouseId(Long productId, Long warehouseId);
}
