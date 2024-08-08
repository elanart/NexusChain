///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.nxc.controllers;
//
//import com.nxc.pojo.Supplier;
//import com.nxc.pojo.User;
//import com.nxc.service.SupplierService;
//import java.util.Map;
//import java.util.UUID;
//import javax.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// *
// * @author tuann
// */
//@Controller
//public class SupplierController {
//
//    @Autowired
//    private SupplierService supplierService;
//
//    @GetMapping("/supplier")
//    public String showSupplierForm(Model model) {
//        model.addAttribute("user", new User());
//        model.addAttribute("supplier", new Supplier());
//        return "supplier";
//    }
//
////    @PostMapping("/supplier")
////    public String createSupplier(Model model, @ModelAttribute(value = "supplier") @Valid Supplier s, BindingResult rs) {
////        if (rs.hasErrors()) {
////            return "supplier";
////        }
////        return "redirect:/supplier";
////    }
//    @PostMapping("/supplier")
//    public String createSupplier(Model model,
//            @ModelAttribute(value = "user") @Valid User u,
//            @ModelAttribute(value = "supplier") @Valid Supplier s,
//            BindingResult rs) {
//        if (rs.hasErrors()) {
//            return "supplier";
//        }
//
//        // Set user to supplier
//        s.setUser(u);
//
//        // Save supplier using service
//        this.supplierService.addOrUpdate(s);
//
//        return "redirect:/supplier";
//    }
////    @ModelAttribute("/supplier")
////    public String supplierView(Model model) {
////        model.addAttribute("supplier", new Supplier());
////        return "supplier";
////    }
//
////    @PostMapping("/supplier")
////    public String create(Model model, @ModelAttribute(value = "supplier") @Valid Supplier s,
////            BindingResult rs) {
////        if (rs.hasErrors()) {
////            return "supplier";
////        }
////        if (s.getId() == null) {
////            s.setId(UUID.randomUUID());
////        }
////        this.supplierService.addOrUpdate(s);
////        return "redirect:/";
////    }
//}
