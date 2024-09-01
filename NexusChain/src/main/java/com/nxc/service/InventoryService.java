package com.nxc.service;

import com.nxc.pojo.Inventory;

import java.util.List;

public interface InventoryService {
    void addInventory(Inventory inventory);
    Inventory getInventory(Long inventoryId);
    List<Inventory> getAllInventories();
    void updateInventory(Inventory inventory);
    void deleteInventory(Long inventoryId);
    void updateInventoryById(Long orderId);
}
