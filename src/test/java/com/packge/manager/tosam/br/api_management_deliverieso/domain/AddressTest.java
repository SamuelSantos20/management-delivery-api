package com.packge.manager.tosam.br.api_management_deliverieso.domain;

import com.packge.manager.tosam.br.api_management_deliverieso.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressTest {

    @Autowired
    private AddressService addressService;

    @Test
    void Delete(){

        var id = "0e24c744-c490-47fc-a60f-4b5846575d47";

        UUID uuid = UUID.fromString(id);

        Optional<Address> addressOptional = Optional.ofNullable(addressService.GetDetails(uuid).orElseGet(() -> null));


        addressService.Delete(addressOptional.get());


    }

}