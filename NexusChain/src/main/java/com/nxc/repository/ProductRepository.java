package com.nxc.repository;

import com.nxc.pojo.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findAll();
    void saveOrUpdate(Product product);
    void delete(Product product);
}
