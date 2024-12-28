package com.packge.manager.tosam.br.api_management_deliverieso.domain;


import com.packge.manager.tosam.br.api_management_deliverieso.service.DeliveryManService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class DeliveryManTest {

    @Autowired
    private  DeliveryManService deliveryManService;

    @Test
     void saveTest() {

         DeliveryMan deliveryMan = new DeliveryMan();

         deliveryMan.setName("Samuel Santos Miranda ");
         deliveryMan.setStatusDeliveryMan(StatusDeliveryMan.ACTIVE);
         deliveryMan.setCpf("17246724796");
         deliveryMan.setTelephone("21978954224");

         deliveryManService.save(deliveryMan);


    }

    @Test
    void GetDetailsTest(){
        var id = "cbfdfa6a-81d6-4348-ab8e-8b8a1bd62ee4";

        var delivery = deliveryManService.GetDetails(UUID.fromString(id));

        List<DeliveryMan> deliveryMEN = new ArrayList<>();

        deliveryMEN.add(delivery.get());

        deliveryMEN.forEach(System.out::println);

    }

    @Test
    void UpdateTest(){
        var id = "cbfdfa6a-81d6-4348-ab8e-8b8a1bd62ee4";

        var delivery = deliveryManService.GetDetails(UUID.fromString(id));

        delivery.get().setStatusDeliveryMan(StatusDeliveryMan.INACTIVE);

        deliveryManService.save(delivery.get());



    }





}