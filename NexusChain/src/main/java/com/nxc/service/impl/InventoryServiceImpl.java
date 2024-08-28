package com.nxc.service.impl;

import com.nxc.pojo.Inventory;
import com.nxc.repository.InventoryRepository;
import com.nxc.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;


    @Override
    public void addInventory(Inventory inventory) {
        this.inventoryRepository.saveOrUpdate(inventory);
    }

    @Override
    public Inventory getInventory(Long inventoryId) {
        return this.inventoryRepository.findById(inventoryId);
    }

    @Override
    public List<Inventory> getAllInventories() {
        return this.inventoryRepository.findAll();
    }

    @Override
    public void updateInventory(Inventory inventory) {
        this.inventoryRepository.saveOrUpdate(inventory);
    }

    @Override
    public void deleteInventory(Long inventoryId) {
        this.inventoryRepository.delete(this.inventoryRepository.findById(inventoryId));
    }
}
