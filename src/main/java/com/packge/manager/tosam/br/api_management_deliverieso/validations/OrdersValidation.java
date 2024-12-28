package com.packge.manager.tosam.br.api_management_deliverieso.validations;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Orders;
import com.packge.manager.tosam.br.api_management_deliverieso.exceptions.DuplicateRecordException;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrdersValidation {

    @Autowired
    private OrdersRepository ordersRepository;


    public  void validate(Orders orders) {


        if (ordersRepository.existsOrderWithCustomerAndAddresses(orders.getId())){

            throw new DuplicateRecordException("Esse pedido já está cadastrado no sistema!");
        }

       else if (existeOrder(orders)) {

            throw new DuplicateRecordException("Esse pedido já está cadastrado no sistema!");
        }

    }

    private boolean existeOrder(Orders orders) {

        Optional<Orders> ordersOptional  = ordersRepository.findByOrderCode(orders.getOrderCode());

        if(orders.getId() == null){

            return ordersOptional.isPresent();
        }




        return ordersOptional.map(Orders::getId).stream().anyMatch(id ->!id.equals(orders.getId()) );
    }






}
