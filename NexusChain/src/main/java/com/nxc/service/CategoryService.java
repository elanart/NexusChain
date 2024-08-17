package com.nxc.service;

import com.nxc.dto.category.request.CategoryRequestDTO;
import com.nxc.dto.category.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO findCategoryById(Long id);
    List<CategoryResponseDTO> findAllCategories();
    CategoryResponseDTO addOrUpdateCategory(CategoryRequestDTO categoryRequestDTO);
    void deleteCategory(Long id);
}
