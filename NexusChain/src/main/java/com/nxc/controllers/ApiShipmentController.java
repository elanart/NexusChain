package com.nxc.controllers;

import com.nxc.dto.shipment.request.ShipmentRequestDTO;
import com.nxc.dto.shipment.response.ShipmentResponseDTO;
import com.nxc.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
public class ApiShipmentController {
    private final ShipmentService shipmentService;

    @PostMapping("/schedule")
    public ResponseEntity<ShipmentResponseDTO> scheduleShipment(@RequestBody ShipmentRequestDTO requestDTO) {
        shipmentService.scheduleShipment(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentResponseDTO> getShipmentDetails(@PathVariable Long id) {
        ShipmentResponseDTO shipment = shipmentService.getShipmentDetails(id);
        if (shipment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(shipment);
    }

    @GetMapping
    public ResponseEntity<List<ShipmentResponseDTO>> getAllShipments() {
        List<ShipmentResponseDTO> shipments = shipmentService.getAllShipments();
        return ResponseEntity.ok(shipments);
    }

    @PostMapping("/{id}/intransit")
    public ResponseEntity<Void> inTransitShipment(@PathVariable Long id) {
        try {
            shipmentService.inTransitShipment(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{id}/done")
    public ResponseEntity<Void> doneShipment(@PathVariable Long id) {
        try {
            shipmentService.doneShipment(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelShipment(@PathVariable Long id) {
        try {
            shipmentService.cancelShipment(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
