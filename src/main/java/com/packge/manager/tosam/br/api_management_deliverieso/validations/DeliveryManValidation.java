package com.packge.manager.tosam.br.api_management_deliverieso.validations;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.DeliveryMan;
import com.packge.manager.tosam.br.api_management_deliverieso.exceptions.DuplicateRecordException;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.DeliveryManRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeliveryManValidation {

 @Autowired
 private DeliveryManRepository deliveryManRepository;

    public  void validate(DeliveryMan deliveryMan) {

        if (existsRegistration(deliveryMan)){

           throw  new DuplicateRecordException("O CPF digitado já se encontra cadastrado no sistema.");

        }

    }

    private boolean existsRegistration(DeliveryMan deliveryMan) {

        Optional<DeliveryMan> deliveryMan1 = deliveryManRepository.findByCpf(deliveryMan.getCpf());

        if (deliveryMan.getId() == null){

            return deliveryMan1.isPresent();
        }

        return deliveryMan1.map( DeliveryMan :: getId).stream().anyMatch( id -> !id.equals(deliveryMan.getId()));


    }

}
