package com.packge.manager.tosam.br.api_management_deliverieso.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("PENDING"),

    IN_ROAD("IN ROAD"),

    DELIVERED("DELIVERED");

    private final String type;

    OrderStatus(String type) {
        this.type = type;
    }


}
