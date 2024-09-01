/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.controllers;

import com.nxc.dto.warehouse.response.WarehouseResponseDTO;
import com.nxc.dto.warehouse.resquest.WarehouseResquestDTO;
import com.nxc.service.WarehouseService;
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
@RequestMapping("/api/warehouse")
@CrossOrigin
@RequiredArgsConstructor
public class ApiWarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<?> createWarehouse(@RequestBody @Valid WarehouseResquestDTO warehouseResquestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        WarehouseResponseDTO warehouseResponseDTO = this.warehouseService.saveOrUpdate(warehouseResquestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(warehouseResponseDTO);
    }
}
