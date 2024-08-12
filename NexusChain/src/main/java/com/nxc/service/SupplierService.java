/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nxc.service;

import com.nxc.dto.account.request.AccountRequest;
import com.nxc.dto.supplier.request.SupplierRequest;
import com.nxc.dto.supplier.request.SupplierUpdateRequest;
import com.nxc.dto.supplier.response.SupplierResponse;

/**
 *
 * @author tuann
 */
public interface SupplierService {
    SupplierResponse registerSupplier(SupplierRequest request);
    SupplierResponse updateSupplier(Long id, SupplierUpdateRequest request);
    void updateSupplierAccount(Long id, AccountRequest request);
}
