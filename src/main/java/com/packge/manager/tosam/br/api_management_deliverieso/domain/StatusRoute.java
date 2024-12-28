package com.packge.manager.tosam.br.api_management_deliverieso.domain;

import lombok.Getter;

@Getter
public enum StatusRoute {
    PLANNED("PLANNED"),
    IN_PROGRESS("IN PROGRESS"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED");

        private String status;


    StatusRoute(String status) {
        this.status = status;
    }
}
