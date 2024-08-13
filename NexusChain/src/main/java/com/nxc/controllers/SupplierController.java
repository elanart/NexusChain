package com.nxc.controllers;

import com.nxc.dto.account.request.AccountRequest;
import com.nxc.dto.supplier.request.SupplierRequest;
import com.nxc.dto.supplier.request.SupplierUpdateRequest;
import com.nxc.dto.supplier.response.SupplierResponse;
import com.nxc.service.CloudinaryService;
import com.nxc.service.SupplierService;
import com.nxc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SupplierController {
    private final SupplierService supplierService;
    private final UserService userService;
    private final CloudinaryService cloudinaryService;

    @PostMapping("/register")
    public ResponseEntity<SupplierResponse> registerSupplier(@ModelAttribute SupplierRequest request) {
        SupplierResponse response = this.supplierService.registerSupplier(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping("/current_user")
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SupplierResponse> updateSupplier(
            @PathVariable Long id,
            @RequestBody SupplierUpdateRequest request) {
        SupplierResponse response = this.supplierService.updateSupplier(id, request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}/account")
    @ResponseStatus(HttpStatus.OK)
    public void updateSupplierAccount(
            @PathVariable Long id,
            @RequestBody AccountRequest request) {
        this.supplierService.updateSupplierAccount(id, request);
    }
}
