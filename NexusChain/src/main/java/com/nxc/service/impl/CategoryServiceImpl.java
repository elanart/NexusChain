package com.nxc.service.impl;

import com.nxc.dto.category.request.CategoryRequestDTO;
import com.nxc.dto.category.response.CategoryResponseDTO;
import com.nxc.pojo.Category;
import com.nxc.repository.CategoryRepository;
import com.nxc.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO findCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id);

        return CategoryResponseDTO.builder()
                .categoryName(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    public List<CategoryResponseDTO> findAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();

        return categories.stream()
                .map(category -> CategoryResponseDTO.builder()
                        .categoryName(category.getName())
                        .description(category.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO addOrUpdateCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = Category.builder()
                .name(categoryRequestDTO.getName())
                .description(categoryRequestDTO.getDescription())
                .build();

        this.categoryRepository.saveOrUpdate(category);

        return CategoryResponseDTO.builder()
                .categoryName(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = this.categoryRepository.findById(id);
        this.categoryRepository.delete(category);
    }
}
