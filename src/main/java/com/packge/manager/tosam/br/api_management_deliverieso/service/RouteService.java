package com.packge.manager.tosam.br.api_management_deliverieso.service;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Orders;
import com.packge.manager.tosam.br.api_management_deliverieso.domain.Route;
import com.packge.manager.tosam.br.api_management_deliverieso.exceptions.DuplicateRecordException;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.RouteRepository;
import com.packge.manager.tosam.br.api_management_deliverieso.validations.RouteValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;

    private final RouteValidation routeValidation;

    public  void save(Route route) {

        if (OrdersIds(route.getOrders())){

            throw new DuplicateRecordException("O pedido informado já se encontra cadastrado no sistema!");

        }

        routeValidation.validate(route);

        routeRepository.save(route);

    }


    public Optional<Route> GetDetails(UUID id) {

        if (null == id){

            throw new IllegalArgumentException("Id não passado!");

        }


        return routeRepository.findById(id);

    }


    private boolean OrdersIds(List<Orders> orders) {

        List<UUID> collect = orders.stream().map(Orders::getId).toList();

        return routeRepository.existsByOrdersIds(collect);

    }

    public void Upate(Route route) {

        routeValidation.validate(route);
        routeRepository.save(route);


    }

    public void Delete(UUID id) {

        routeRepository.deleteById(id);

    }
}
