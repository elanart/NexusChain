/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.controllers;

import com.nxc.service.CategoryService;
import com.nxc.service.SupplierService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author tuann
 */
@Controller
public class HomeController {
    @Autowired
    private SupplierService supplierService;
    
    @Autowired
    private CategoryService categoryService;
    
    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        
//        model.addAttribute("suppliers", this.supplierService.getSuppliers(params));
        model.addAttribute("categories", this.categoryService.getAllCategories());
        
        return "home";
    }
    
}
