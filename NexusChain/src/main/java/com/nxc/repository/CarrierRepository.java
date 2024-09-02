package com.nxc.repository;

import com.nxc.pojo.Carrier;

import java.util.List;

public interface CarrierRepository {
    void saveCarrier(Carrier carrier);
    Carrier findById(Long id);
    List<Carrier> findAll();
    void delete(Carrier carrier);
}
