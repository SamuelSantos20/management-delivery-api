package com.packge.manager.tosam.br.api_management_deliverieso.domain;

import lombok.Getter;

@Getter
public enum StatusDeliveryMan {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String status;

    StatusDeliveryMan(String status) {
        this.status = status;
    }


}
