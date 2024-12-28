package com.packge.manager.tosam.br.api_management_deliverieso.domain;

import com.packge.manager.tosam.br.api_management_deliverieso.dto.CustomerDTO;
import com.packge.manager.tosam.br.api_management_deliverieso.mappers.CustomerMapper;
import com.packge.manager.tosam.br.api_management_deliverieso.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class CustomerTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    void saveTest() {

        Customer customer = new Customer();

        customer.setName("Fabricio Bruno");
        customer.setCpf("17246724796");
        customer.setTelephone("21978954224");

        Address address = new Address();

        List<Customer> customers = new ArrayList<>();

        customers.add(customer);

        address.setCity("Nova York");
        address.setComplement("US");
        address.setStreet("street 1");
        address.setNeighborhood("Brooklin");
        address.setCustomers(customers);
        address.setAddressType(AddressType.CUSTOMER_LOCATION);
        address.setNumber(123);



        customer.setAddress(address);

        customerService.save(customer);


    }

    @Test
    void GetDetailsTest() {

        var id = "e9229687-94cb-4acf-bafe-698946a1183f";

        Optional<Customer> customer = Optional.ofNullable(customerService.GetDetails(UUID.fromString(id)).orElseGet(() -> null));

        var dto = customerMapper.toDTO(customer.get());

        List<CustomerDTO> customerDTOS = new ArrayList<>();
        customerDTOS.add(dto);

        customerDTOS.forEach(System.out::println);
    }


    @Test
    void UpdateTest() {

        var id = "e9229687-94cb-4acf-bafe-698946a1183f";

        Optional<Customer> customer = customerService.GetDetails(UUID.fromString(id));

        Address address = customer.get().getAddress();

        address.setCity("Nova Iguaçu");

        List<Address> addresses = new ArrayList<>();

        addresses.add(address);

        customer.get().setAddress(address);

        customerService.save(customer.get());
    }

    @Test
    void Delete() {
        var id = "e9229687-94cb-4acf-bafe-698946a1183f";
        UUID uuid = UUID.fromString(id);

        customerService.Delete(uuid);


    }


}