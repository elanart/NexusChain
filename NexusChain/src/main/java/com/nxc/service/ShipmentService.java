package com.nxc.service;

import com.nxc.dto.shipment.request.ShipmentRequestDTO;
import com.nxc.dto.shipment.response.ShipmentResponseDTO;

import java.util.List;

public interface ShipmentService {
    void scheduleShipment(ShipmentRequestDTO shipmentRequestDTO);
    ShipmentResponseDTO getShipmentDetails(Long id);
    List<ShipmentResponseDTO> getAllShipments();
    void inTransitShipment(Long id);
    void doneShipment(Long id);
    void cancelShipment(Long id);
}
