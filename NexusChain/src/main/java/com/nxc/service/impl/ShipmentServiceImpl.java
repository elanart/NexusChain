package com.nxc.service.impl;

import com.nxc.dto.shipment.request.ShipmentRequestDTO;
import com.nxc.dto.shipment.response.ShipmentResponseDTO;
import com.nxc.enums.ShippingStatusEnum;
import com.nxc.pojo.Carrier;
import com.nxc.pojo.Shipment;
import com.nxc.pojo.Warehouse;
import com.nxc.repository.CarrierRepository;
import com.nxc.repository.ShipmentRepository;
import com.nxc.repository.WarehouseRepository;
import com.nxc.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final CarrierRepository carrierRepository;
    private final WarehouseRepository warehouseRepository;

    @Override
    public void scheduleShipment(ShipmentRequestDTO shipmentRequestDTO) {
        Carrier carrier = carrierRepository.findById(shipmentRequestDTO.getCarrierId());
        if(carrier == null) {
            throw new IllegalArgumentException("Đơn vị vận chuyển không tồn tại");
        }

        Warehouse warehouse = warehouseRepository.findById(shipmentRequestDTO.getWarehouseId());
        if(warehouse == null) {
            throw new IllegalArgumentException("Kho không tồn tại");
        }

        Shipment shipment = Shipment.builder()
                .shipmentDate(shipmentRequestDTO.getShipmentDate())
                .trackingNumber(shipmentRequestDTO.getTrackingNumber())
                .expectedDelivery(shipmentRequestDTO.getExpectedDelivery())
                .cost(shipmentRequestDTO.getCost())
                .destination(shipmentRequestDTO.getDestination())
                .carrier(carrier)
                .warehouse(warehouse)
                .status(ShippingStatusEnum.SHIPPED)
                .build();

        shipmentRepository.saveOrUpdate(shipment);
    }

    @Override
    public ShipmentResponseDTO getShipmentDetails(Long id) {
        Shipment shipment = shipmentRepository.findById(id);
        if(shipment == null) {
            throw new IllegalArgumentException("Đơn vận chuyển không tồn tại");
        }
        return mapToShipmentResponseDTO(shipment);
    }

    @Override
    public List<ShipmentResponseDTO> getAllShipments() {
        return shipmentRepository.findAll()
                .stream()
                .map(this::mapToShipmentResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void inTransitShipment(Long id) {
        Shipment shipment = shipmentRepository.findById(id);
        if(shipment == null) {
            throw new IllegalArgumentException("Đơn vận chuyển không tồn tại");
        }
        shipment.setStatus(ShippingStatusEnum.IN_TRANSIT);
        shipmentRepository.saveOrUpdate(shipment);
    }

    @Override
    public void doneShipment(Long id) {
        Shipment shipment = shipmentRepository.findById(id);
        if(shipment == null) {
            throw new IllegalArgumentException("Đơn vận chuyển không tồn tại");
        }
        shipment.setStatus(ShippingStatusEnum.DELIVERED);
        shipmentRepository.saveOrUpdate(shipment);
    }

    @Override
    public void cancelShipment(Long id) {
        Shipment shipment = shipmentRepository.findById(id);
        if(shipment == null) {
            throw new IllegalArgumentException("Đơn vận chuyển không tồn tại");
        }
        shipment.setStatus(ShippingStatusEnum.RETURNED);
        shipmentRepository.saveOrUpdate(shipment);
    }

    private ShipmentResponseDTO mapToShipmentResponseDTO(Shipment shipment) {
        return ShipmentResponseDTO.builder()
                .id(shipment.getId())
                .shipmentDate(shipment.getShipmentDate())
                .trackingNumber(shipment.getTrackingNumber())
                .expectedDelivery(shipment.getExpectedDelivery())
                .cost(shipment.getCost())
                .destination(shipment.getDestination())
                .status(shipment.getStatus().toString())
                .carrierName(shipment.getCarrier().getUser().getFullName())
                .warehouseLocation(shipment.getWarehouse().getLocation())
                .build();
    }
}
