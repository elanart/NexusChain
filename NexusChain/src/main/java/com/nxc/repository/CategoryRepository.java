package com.nxc.repository;

import com.nxc.pojo.Category;

import java.util.List;

public interface CategoryRepository {
    Category findById(Long id);
    List<Category> findAll();
    void saveOrUpdate(Category category);
    void delete(Category category);
}
