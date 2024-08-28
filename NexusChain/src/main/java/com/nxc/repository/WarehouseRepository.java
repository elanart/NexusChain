package com.nxc.repository;

import com.nxc.pojo.Warehouse;

import java.util.List;

public interface WarehouseRepository {
    void saveOrUpdate(Warehouse warehouse);
    Warehouse findById(Long id);
    void delete(Warehouse warehouse);
    List<Warehouse> findAll();
}
