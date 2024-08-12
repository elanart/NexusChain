package com.nxc.controllers;

import com.nxc.dto.supplier.request.SupplierRegistrationRequest;
import com.nxc.dto.supplier.response.SupplierRegistrationResponse;
import com.nxc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/suppliers")
//@CrossOrigin(origins = "http://127.0.0.1:5173/", allowedHeaders = "*", allowCredentials = "true")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping("/register")
    public ResponseEntity<SupplierRegistrationResponse> registerSupplier(@RequestBody SupplierRegistrationRequest request) {
        SupplierRegistrationResponse response = supplierService.registerSupplier(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
