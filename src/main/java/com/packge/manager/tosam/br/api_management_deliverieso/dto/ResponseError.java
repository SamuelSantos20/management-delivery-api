package com.packge.manager.tosam.br.api_management_deliverieso.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ResponseError(int status , String message, List<ErrorField> field) {



    public static ResponseError conflict(String message) {

        return new ResponseError(HttpStatus.CONFLICT.value() , message, List.of());

    }


    public static ResponseError StandardResponse(String message) {

        return new ResponseError(HttpStatus.BAD_REQUEST.value(), message , List.of());
    }
}
