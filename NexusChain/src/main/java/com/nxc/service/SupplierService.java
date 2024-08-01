/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nxc.service;

import com.nxc.pojo.Supplier;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tuann
 */
public interface SupplierService {
    List<Supplier> getSuppliers(Map<String, String> params);
    void addOrUpdate(Supplier supplier);
}
