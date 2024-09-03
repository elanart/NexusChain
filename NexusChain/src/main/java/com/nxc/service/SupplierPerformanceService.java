package com.nxc.service;

import com.nxc.dto.supplier.request.SupplierPerformanceDTO;
import com.nxc.dto.supplier.request.SupplierPerformanceRequestDTO;

import java.util.List;

public interface SupplierPerformanceService {
    List<SupplierPerformanceDTO> getSupplierPerformanceReport();
}
