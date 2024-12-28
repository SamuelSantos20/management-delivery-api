package com.packge.manager.tosam.br.api_management_deliverieso.service;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.DeliveryMan;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.DeliveryManRepository;
import com.packge.manager.tosam.br.api_management_deliverieso.validations.DeliveryManValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryManService {

    private final DeliveryManRepository deliveryManRepository;

    private final DeliveryManValidation deliveryManValidation;

    public  void save(DeliveryMan deliveryMan) {

        deliveryManValidation.validate(deliveryMan);

        deliveryManRepository.save(deliveryMan);

    }

    public Optional<DeliveryMan> GetDetails(UUID id) {

        return deliveryManRepository.findById(id);

    }


    public void Update(DeliveryMan deliveryMan) {

        deliveryManValidation.validate(deliveryMan);

        deliveryManRepository.save(deliveryMan);

    }

    public void Delete(DeliveryMan deliveryMan) {

        deliveryManRepository.delete(deliveryMan);

    }
}
