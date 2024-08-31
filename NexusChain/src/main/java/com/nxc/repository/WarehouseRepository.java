/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nxc.repository;

import com.nxc.pojo.Warehouse;

/**
 *
 * @author tuann
 */
public interface WarehouseRepository {
    Warehouse findById(String id);
    void saveOrUpdate(Warehouse warehouse);
}
