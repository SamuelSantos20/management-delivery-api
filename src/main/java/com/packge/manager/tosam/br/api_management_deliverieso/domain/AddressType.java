package com.packge.manager.tosam.br.api_management_deliverieso.domain;

import lombok.Getter;

@Getter
public enum AddressType {

    DELIVERY_ORIGIN("DELIVERY ORIGIN"),

    CUSTOMER_LOCATION("CUSTOMER LOCATION");


    private String addresstype;


    AddressType(String addresstype) {
        this.addresstype = addresstype;
    }




}
