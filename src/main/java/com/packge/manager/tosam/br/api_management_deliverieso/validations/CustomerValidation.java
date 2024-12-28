package com.packge.manager.tosam.br.api_management_deliverieso.validations;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Customer;
import com.packge.manager.tosam.br.api_management_deliverieso.exceptions.DuplicateRecordException;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.CustomerRepository;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerValidation {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    public void validate(Customer customer) {

        Optional<Customer> customerOptional = Optional.ofNullable(customerRepository.findByCpf(customer.getCpf()).orElseGet(() -> null));

        if (existsRegistration(customer)) {

            throw new DuplicateRecordException("O CPF digitado já se encontra cadastrado no sistema.");

        }

    }

    private boolean existsRegistration(Customer customer) {


        Optional<Customer> optionalCustomer = customerRepository.findByCpf(customer.getCpf());

        if (customer.getId() == null) {

            optionalCustomer.isPresent();
        }


        return optionalCustomer.map(Customer :: getId).stream().anyMatch( id ->!id.equals(customer.getId()));

    }


}
