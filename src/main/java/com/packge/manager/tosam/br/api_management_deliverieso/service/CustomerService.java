package com.packge.manager.tosam.br.api_management_deliverieso.service;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Address;
import com.packge.manager.tosam.br.api_management_deliverieso.domain.Customer;
import com.packge.manager.tosam.br.api_management_deliverieso.exceptions.OperationNotPermitted;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.AddressRepository;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.CustomerRepository;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.OrdersRepository;
import com.packge.manager.tosam.br.api_management_deliverieso.validations.CustomerValidation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerValidation customerValidation;

    private final OrdersRepository ordersRepository;

    private final AddressRepository addressRepository;


    public void save(Customer customer) {
        customerValidation.validate(customer);

        addressRepository.save(customer.getAddress());

        customerRepository.save(customer);


    }


    public Optional<Customer> GetDetails(UUID id) {

        return customerRepository.findById(id);

    }

    public void Update(Customer customer) {

        customerValidation.validate(customer);

        addressRepository.save(customer.getAddress());

        customerRepository.save(customer);
    }


    public void Delete(UUID customerId) {

        boolean hasOrders = ordersRepository.existsByCustomerId(customerId);

        if (hasOrders) {
            throw new OperationNotPermitted("Não é possível excluir o cliente, pois há pedidos associados " +
                    "aos endereços.");
        }
        Optional<Customer> customer = customerRepository.findById(customerId);

        addressRepository.delete(customer.get().getAddress());

        customerRepository.deleteById(customer.get().getId());
    }


}
