package com.nxc.controllers;

import com.nxc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/")
    public String getCategories(Model model){
        model.addAttribute("cates", this.categoryService.getAllCategories());
        return "categories";
    }
}
