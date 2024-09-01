package com.nxc.service.impl;

import com.nxc.enums.OrderTypeEnum;
import com.nxc.pojo.*;
import com.nxc.repository.InventoryRepository;
import com.nxc.repository.OrderRepository;
import com.nxc.repository.ProductRepository;
import com.nxc.repository.WarehouseRepository;
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
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;


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

    @Override
    public void updateInventoryById(Long orderId) {
        Order order = orderRepository.findById(orderId);
        Long warehouseId = order.getWarehouse().getId();

        for (OrderDetail detail : order.getOrderDetails()) {
            Long productId = detail.getProduct().getId();
            int quantity = detail.getQuantity();

            Inventory inventory = inventoryRepository.findByProductIdAndWarehouseId(productId, warehouseId);

            if (inventory == null && order.getType() == OrderTypeEnum.INBOUND) {
                // INBOUND order with no existing inventory, create new inventory entry
                Product product = productRepository.findById(productId);
                Warehouse warehouse = warehouseRepository.findById(warehouseId);

                inventory = new Inventory();
                inventory.setProduct(product);
                inventory.setWarehouse(warehouse);
                inventory.setQuantity(quantity);
            } else if (inventory != null) {
                if (order.getType() == OrderTypeEnum.INBOUND) {
                    // Increase inventory for INBOUND orders
                    inventory.setQuantity(inventory.getQuantity() + quantity);
                } else if (order.getType() == OrderTypeEnum.OUTBOUND) {
                    // Decrease inventory for OUTBOUND orders
                    inventory.setQuantity(inventory.getQuantity() - quantity);
                }
            }

            if (inventory != null) {
                inventoryRepository.saveOrUpdate(inventory);
            }
        }
    }

}
