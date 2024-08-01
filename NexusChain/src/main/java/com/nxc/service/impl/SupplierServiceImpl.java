/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.service.impl;

import com.cloudinary.Cloudinary;
import com.nxc.pojo.Supplier;
import com.nxc.repository.SupplierRepository;
import com.nxc.service.SupplierService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tuann
 */
@Service
public class SupplierServiceImpl implements SupplierService{
    @Autowired
    private SupplierRepository supplierRepository;
       
    public List<Supplier> getSuppliers(Map<String, String> params) {
        return this.supplierRepository.getSuppliers(params);
    }
    
    public void addOrUpdate(Supplier supplier) {
        this.supplierRepository.addOrUpdate(supplier);
    }
}
