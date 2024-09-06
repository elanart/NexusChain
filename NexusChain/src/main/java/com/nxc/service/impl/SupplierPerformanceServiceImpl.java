package com.nxc.service.impl;

import com.nxc.dto.supplier.request.SupplierPerformanceDTO;
import com.nxc.dto.supplier.request.SupplierPerformanceRequestDTO;
import com.nxc.repository.SupplierPerformanceRepository;
import com.nxc.service.SupplierPerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierPerformanceServiceImpl implements SupplierPerformanceService {
    private final SupplierPerformanceRepository supplierPerformanceRepository;

    @Override
    public List<SupplierPerformanceDTO> getSupplierPerformanceReport() {
        return supplierPerformanceRepository.getSupplierPerformance();
    }
}
