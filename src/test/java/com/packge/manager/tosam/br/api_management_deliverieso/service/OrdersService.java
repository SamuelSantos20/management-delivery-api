package com.packge.manager.tosam.br.api_management_deliverieso.service;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Orders;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;


    public  void save(Orders orders) {

        ordersRepository.save(orders);

    }

    public  void Update(Orders orders) {

        ordersRepository.save(orders);

    }


    public Optional<Orders>  GetDetails(UUID id) {

        return ordersRepository.findById(id);

    }

    public void Delete(UUID uuid) {

        ordersRepository.deleteById(uuid);

    }








}
