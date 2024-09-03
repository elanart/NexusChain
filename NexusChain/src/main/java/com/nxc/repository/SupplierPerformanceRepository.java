package com.nxc.repository;

import com.nxc.dto.supplier.request.SupplierPerformanceDTO;

import java.util.Date;
import java.util.List;

public interface SupplierPerformanceRepository {
    List<SupplierPerformanceDTO> getSupplierPerformance();
}
