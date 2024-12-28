package com.packge.manager.tosam.br.api_management_deliverieso.domain;

import com.packge.manager.tosam.br.api_management_deliverieso.service.CustomerService;
import com.packge.manager.tosam.br.api_management_deliverieso.service.OrdersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class OrderTest {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CustomerService customerService;

    @Test
    void saveTest(){

        var customer_id = "e9229687-94cb-4acf-bafe-698946a1183f";
        Optional<Customer> customer = customerService.GetDetails(UUID.fromString(customer_id));

        Orders orders = new Orders();

        List<Customer> customers = new ArrayList<>();

        customers.add(customer.get());

        Address address = new Address();
        address.setCity("Nova York");
        address.setComplement("Casa 1");
        address.setStreet("street 2");
        address.setNeighborhood("California");
        address.setNumber(123);
        address.setAddressType(AddressType.DELIVERY_ORIGIN);
        address.setCustomers(customers);

        orders.setCustomer(customer.get());
        orders.setWeight(7.0);
        orders.setOrderStatus(OrderStatus.IN_ROAD);
        orders.setOrigin(address);

        ordersService.save(orders);


    }

    @Test
    void GetDetailsTest(){

        var id = "8485644e-7198-469c-be72-34ba2a71e7cc";

        Optional<Orders> orders = Optional.ofNullable(ordersService.GetDetails(UUID.fromString(id)).orElseGet(()->null));

        List<Orders> ordersList = new ArrayList<>();

        ordersList.add(orders.get());

        ordersList.forEach(System.out::println);

    }


    @Test
    void Update(){
       var id = "d7e84d5b-1d1b-40c6-9052-20e64708973f";

        Optional<Orders> ordersOptional = Optional.ofNullable(ordersService.GetDetails(UUID.fromString(id)).orElseGet(() -> null));

        Address address = ordersOptional.get().getOrigin();

        address.setCity("Boston");

        Orders orders = ordersOptional.get();

        orders.setOrderStatus(OrderStatus.DELIVERED);

        orders.setOrigin(address);

        ordersService.Update(orders);

    }


    @Test
    void DeleteTest(){

        var id = "d7e84d5b-1d1b-40c6-9052-20e64708973f";


        UUID uuid = UUID.fromString(id);

        ordersService.Delete(uuid);



    }




}