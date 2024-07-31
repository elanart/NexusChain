package com.nxc.service.impl;

import com.nxc.pojo.Category;
import com.nxc.repository.CategoryRepository;
import com.nxc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.getAllCategories();
    }
}
