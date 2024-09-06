package com.nxc.service.impl;

import com.nxc.dto.supplier.request.SupplierPerformanceDTO;
import com.nxc.dto.supplier.request.SupplierRatingRequestDTO;
import com.nxc.dto.supplier.response.SupplierRatingResponseDTO;
import com.nxc.enums.CriteriaEnum;
import com.nxc.pojo.Supplier;
import com.nxc.pojo.SupplierRating;
import com.nxc.pojo.User;
import com.nxc.repository.SupplierRatingRepository;
import com.nxc.service.SupplierRatingService;
import com.nxc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierRatingServiceImpl implements SupplierRatingService {
    private final SupplierRatingRepository supplierRatingRepository;
    private final UserService userService;

    @Override
    public void rateSupplier(SupplierRatingRequestDTO dto) {
        User user = userService.findById(dto.getSupplierId());
        Supplier supplier = user.getSupplier();

        if (supplier == null) {
            throw new RuntimeException("Supplier not found");
        }

        SupplierRating rating = SupplierRating.builder()
                .supplier(supplier)
                .criterion(dto.getCriterion())
                .rating(dto.getRating())
                .comment(dto.getComment())
                .createdDate(new Date())
                .build();

        supplierRatingRepository.save(rating);

        convertToResponseDTO(rating);
    }

    @Override
    public List<SupplierRatingResponseDTO> getRatingsBySupplier(Long supplierId) {
        return supplierRatingRepository.findBySupplierId(supplierId)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRating(Long id) {
        supplierRatingRepository.deleteById(id);
    }

//    private BigDecimal calculateAverageRating(List<SupplierRating> ratings, CriteriaEnum criterion) {
//        double average = ratings.stream()
//                .filter(r -> r.getCriterion() == criterion)
//                .map(SupplierRating::getRating)
//                .mapToDouble(BigDecimal::doubleValue)
//                .average()
//                .orElse(0.0);
//        return BigDecimal.valueOf(average);
//    }

    private SupplierRatingResponseDTO convertToResponseDTO(SupplierRating rating) {
        SupplierRatingResponseDTO dto = new SupplierRatingResponseDTO();
        dto.setId(rating.getId());
        dto.setCriterion(rating.getCriterion());
        dto.setRating(rating.getRating());
        dto.setComment(rating.getComment());
        dto.setCreatedDate(rating.getCreatedDate());
        return dto;
    }
}
