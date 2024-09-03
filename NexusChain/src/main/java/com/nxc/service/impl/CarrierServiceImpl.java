package com.nxc.service.impl;

import com.nxc.dto.carrier.request.CarrierRequestDTO;
import com.nxc.dto.carrier.respone.CarrierResponseDTO;
import com.nxc.pojo.Carrier;
import com.nxc.pojo.User;
import com.nxc.repository.CarrierRepository;
import com.nxc.repository.UserRepository;
import com.nxc.service.CarrierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarrierServiceImpl implements CarrierService {
    private final CarrierRepository carrierRepository;
    private final UserRepository userRepository;

    @Override
    public void registerCarrier(CarrierRequestDTO carrierRequestDTO) {
        User user = userRepository.findById(carrierRequestDTO.getUserId());

        Carrier carrier = Carrier.builder()
                .user(user)
                .rating(carrierRequestDTO.getRating())
                .cooperationTerms(carrierRequestDTO.getCooperationTerms())
                .build();

        carrierRepository.saveCarrier(carrier);
    }

    @Override
    public CarrierResponseDTO getCarrierDetails(Long id) {
        Carrier carrier = carrierRepository.findById(id);
        return mapToCarrierResponseDTO(carrier);
    }

    @Override
    public List<CarrierResponseDTO> getAllCarriers() {
        return carrierRepository.findAll()
                .stream()
                .map(this::mapToCarrierResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void removeCarrier(Long id) {
        Carrier carrier = carrierRepository.findById(id);
        carrierRepository.delete(carrier);
    }

    private CarrierResponseDTO mapToCarrierResponseDTO(Carrier carrier) {
        return CarrierResponseDTO.builder()
                .id(carrier.getId())
                .name(carrier.getUser().getFullName())
                .rating(carrier.getRating())
                .cooperationTerms(carrier.getCooperationTerms())
                .build();
    }
}
