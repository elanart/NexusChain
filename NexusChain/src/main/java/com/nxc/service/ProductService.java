package com.nxc.service;

import com.nxc.dto.product.request.ProductRequestDTO;
import com.nxc.dto.product.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO findProductById(Long id);
    List<ProductResponseDTO> findAllProducts();
    ProductResponseDTO addOrUpdateProduct(ProductRequestDTO productRequest);
    void deleteProduct(Long id);
}
