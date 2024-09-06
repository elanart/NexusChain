package com.nxc.service;

import com.nxc.dto.supplier.request.SupplierPerformanceDTO;
import com.nxc.dto.supplier.request.SupplierRatingRequestDTO;
import com.nxc.dto.supplier.response.SupplierRatingResponseDTO;

import java.util.List;

public interface SupplierRatingService {
    void rateSupplier(SupplierRatingRequestDTO dto);
    List<SupplierRatingResponseDTO> getRatingsBySupplier(Long supplierId);
    void deleteRating(Long id);
}
