package com.nxc.controllers;

import com.nxc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    private SupplierService supplierService;
    
    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("supplier", this.supplierService.getSupplier());
        return "home";
    }

}
