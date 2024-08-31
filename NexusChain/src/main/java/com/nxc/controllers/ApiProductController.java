/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.controllers;

import com.nxc.dto.product.request.ProductRequestDTO;
import com.nxc.dto.product.response.ProductResponseDTO;
import com.nxc.service.ProductService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tuann
 */
@RestController
@RequestMapping("/api/product")
@CrossOrigin
@RequiredArgsConstructor
public class ApiProductController {
    private final ProductService productService;
    
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        ProductResponseDTO productResponseDTO = this.productService.addOrUpdateProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDTO);
    }
    
}
