package com.nxc.repository;

import com.nxc.pojo.SupplierRating;

import java.util.List;

public interface SupplierRatingRepository {
    SupplierRating save(SupplierRating supplierRating);
    SupplierRating findById(Long id);
    List<SupplierRating> findBySupplierId(Long supplierId);
    void deleteById(Long id);
}
