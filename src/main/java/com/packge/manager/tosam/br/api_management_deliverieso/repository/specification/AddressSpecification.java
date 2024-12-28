package com.packge.manager.tosam.br.api_management_deliverieso.repository.specification;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Address;
import com.packge.manager.tosam.br.api_management_deliverieso.domain.Customer;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public class AddressSpecification {


    public static Specification<Address> specificationId(UUID id) {

        return (root, query, cb) -> cb.equal(root.get("id") , id);

    }


    public static Specification<Address> specificationStreet(String street) {

        return (root, query, cb) -> cb.like(cb.upper(root.get("street")) , "%" + street.toUpperCase() +"%");

    }

    public static Specification<Address> specificationNumber(Integer number) {

        return (root, query, cb) -> cb.equal(root.get("number") , number);
    }

    public static Specification<Address> specificationCity(String city) {

        return  (root, query, cb) -> cb.like(cb.upper(root.get("city")) , "%"+ city.toUpperCase() +"%");
    }

    public static Specification<Address> specificationNeighborhood(String neighborhood) {

        return (root, query, cb) -> cb.like(cb.upper(root.get("neighborhood")) , "%"+ neighborhood.toUpperCase()+"%");

    }

    public static Specification<Address> specificationComplement(String complement) {

        return (root, query, cb) -> cb.like(cb.upper(root.get("complement")) , "%"+ complement.toUpperCase()+"%");
    }











}
