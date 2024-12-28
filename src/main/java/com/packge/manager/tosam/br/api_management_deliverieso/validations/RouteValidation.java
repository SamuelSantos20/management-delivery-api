package com.packge.manager.tosam.br.api_management_deliverieso.validations;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Route;
import com.packge.manager.tosam.br.api_management_deliverieso.exceptions.DuplicateRecordException;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RouteValidation {

    @Autowired
    private RouteRepository routeRepository;


    public  void validate(Route route) {

        if (existsRoute(route)){

         throw new DuplicateRecordException("Rota duplicada! Já existe uma rota para o entregador '"
                 + route.getDeliveryMan().getId() + "' na data '" + route.getDate() + "'.");

        }

    }

    private boolean existsRoute(Route route) {

        Optional<Route> routeOptional = routeRepository.findByDateAndDeliveryMan(route.getDate(), route.getDeliveryMan());


        if (routeOptional.isEmpty()){

            return false;
        }

        if (null == route.getId()){

            return true;
        }

        return routeOptional.map(Route :: getId).stream().anyMatch(id -> !id.equals(route.getId()));

    }

}
