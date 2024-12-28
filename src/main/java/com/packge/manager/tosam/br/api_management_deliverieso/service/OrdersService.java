package com.packge.manager.tosam.br.api_management_deliverieso.service;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Address;
import com.packge.manager.tosam.br.api_management_deliverieso.domain.OrderStatus;
import com.packge.manager.tosam.br.api_management_deliverieso.domain.Orders;
import com.packge.manager.tosam.br.api_management_deliverieso.exceptions.OperationNotPermitted;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.AddressRepository;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.OrdersRepository;
import static com.packge.manager.tosam.br.api_management_deliverieso.repository.specification.OrdersSpecification.*;
import com.packge.manager.tosam.br.api_management_deliverieso.validations.OrdersValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    private final OrdersValidation ordersValidation;

    private final AddressRepository addressRepository;


    public void save(Orders orders) {

        ordersValidation.validate(orders);

        addressRepository.save(orders.getOrigin());

        ordersRepository.save(orders);
    }

    public void Update(Orders orders) {

        ordersValidation.validate(orders);

        ordersRepository.save(orders);

    }

    public Optional<Orders> GetDetails(UUID id) {

        return ordersRepository.findOrderWithCustomerAndAddresses(id);

    }


    public Page<Orders> OrderSearch(Double weight,

                                    String  orderStatus,

                                    UUID orderCode,

                                    Integer dayCreation,

                                    Integer monthCreation,

                                    Integer yearCreation,

                                    String name,

                                    String cpf,

                                    Integer numberPage,

                                    Integer quantityPage) {


        Specification<Orders> specification = Specification.where((root, query, cb) -> cb.conjunction());


        if (weight != null){

            specification = specification.and(specificationWeight(weight));
        }

        if (orderStatus!=null){

            specification = specification.and(specificationOrderStatus(orderStatus));
        }
        if (orderCode!=null){

            specification =  specification.and(specificationOrderCode(orderCode));
        }

        if (dayCreation!=null||monthCreation!=null||yearCreation!=null){

            specification = specification.and(specificationDate_created(dayCreation, monthCreation, yearCreation));
        }

        if (name!=null || cpf!=null){

            specification = specification.and(specificationCustomer(name, cpf));

        }

        Pageable page = PageRequest.of(numberPage, quantityPage);

        return ordersRepository.findAll(specification, page);

    }


    public void Delete(UUID id) {

        Orders orders = ordersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido com ID " + id + " não encontrado."));


        if (orders.getOrderStatus().equals(OrderStatus.IN_ROAD)) {

            throw new OperationNotPermitted("Não é possivel deletar um pedido enquanto o seu status for de IN ROAD");

        }

        ordersRepository.deleteById(id);

    }
}
