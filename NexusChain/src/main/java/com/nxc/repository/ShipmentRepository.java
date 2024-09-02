package com.nxc.repository;

import com.nxc.pojo.Shipment;

import java.util.List;

public interface ShipmentRepository {
    void saveOrUpdate(Shipment shipment);
    Shipment findById(Long id);
    List<Shipment> findAll();
    void delete(Shipment shipment);
}
