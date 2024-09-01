package com.nxc.service.impl;

import com.nxc.dto.product.request.ProductRequestDTO;
import com.nxc.dto.product.response.ProductResponseDTO;
import com.nxc.pojo.Category;
import com.nxc.pojo.Product;
import com.nxc.pojo.User;
import com.nxc.repository.CategoryRepository;
import com.nxc.repository.ProductRepository;
import com.nxc.repository.UserRepository;
import com.nxc.service.InventoryService;
import com.nxc.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final InventoryService inventoryService;

    @Override
    public ProductResponseDTO findProductById(Long id) {
        Product product = this.productRepository.findById(id);

        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .isDeleted(product.getIsDeleted())
                .categoryName(product.getCategory().getName())
                .build();
    }

    @Override
    public List<ProductResponseDTO> findAllProducts() {
        List<Product> products = this.productRepository.findAll();

        return products.stream()
                .map(product -> ProductResponseDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .isDeleted(product.getIsDeleted())
                        .categoryName(product.getCategory().getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO addOrUpdateProduct(ProductRequestDTO productRequest) {
        Category category = this.categoryRepository.findById(productRequest.getCategoryId());
        User user = this.userRepository.findById(productRequest.getUserId());

        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .category(category)
                .build();

        this.productRepository.saveOrUpdate(product);
        
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .categoryName(category.getName())
                .isDeleted(product.getIsDeleted())
                .build();
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = this.productRepository.findById(id);
        this.productRepository.delete(product);
    }

    @Override
    public List<ProductResponseDTO> findProductsByUserId(Long userId) {
        List<Product> products = this.productRepository.findProductsByUserId(userId);

        return products.stream()
                .map(product -> ProductResponseDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .isDeleted(product.getIsDeleted())
                        .categoryName(product.getCategory().getName())
                        .build())
                .collect(Collectors.toList());
    }
}
