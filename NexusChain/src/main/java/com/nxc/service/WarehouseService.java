package com.nxc.service;

import com.nxc.dto.warehouse.response.WarehouseResponseDTO;
import com.nxc.dto.warehouse.resquest.WarehouseResquestDTO;
import com.nxc.pojo.Account;
import com.nxc.pojo.Warehouse;

import java.util.List;
import javax.persistence.metamodel.SingularAttribute;

public interface WarehouseService {
    void addWarehouse(Warehouse warehouse);
    Warehouse getWarehouse(Long warehouseId);
    List<Warehouse> getAllWarehouses();
    void updateWarehouse(Warehouse warehouse);
    void deleteWarehouse(Long warehouseId);
    void saveOrUpdate(WarehouseResquestDTO warehouseResquestDTO);
}
