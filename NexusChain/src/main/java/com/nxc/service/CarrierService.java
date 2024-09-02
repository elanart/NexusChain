package com.nxc.service;

import com.nxc.dto.carrier.request.CarrierRequestDTO;
import com.nxc.dto.carrier.respone.CarrierResponseDTO;

import java.util.List;

public interface CarrierService {
    void registerCarrier(CarrierRequestDTO carrierRequestDTO);
    CarrierResponseDTO getCarrierDetails(Long id);
    List<CarrierResponseDTO> getAllCarriers();
    void removeCarrier(Long id);
}
