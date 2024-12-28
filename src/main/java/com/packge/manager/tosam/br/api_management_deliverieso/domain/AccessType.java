package com.packge.manager.tosam.br.api_management_deliverieso.domain;

import lombok.Getter;

@Getter
public enum AccessType {

    DELIVERYMAN("DELIVERYMAN"),

    ADMINISTRATOR("ADMINISTRATOR"),

    CUSTOMER("CUSTOMER");

    private final String Accesstype;

    AccessType(String Accesstype) {
        this.Accesstype = Accesstype;
    }




}
