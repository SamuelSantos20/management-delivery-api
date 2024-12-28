package com.packge.manager.tosam.br.api_management_deliverieso.config.common;

import com.packge.manager.tosam.br.api_management_deliverieso.dto.ErrorField;
import com.packge.manager.tosam.br.api_management_deliverieso.dto.ResponseError;
import com.packge.manager.tosam.br.api_management_deliverieso.exceptions.DuplicateRecordException;
import com.packge.manager.tosam.br.api_management_deliverieso.exceptions.OperationNotPermitted;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.List;

@RestControllerAdvice
public class GlobaException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseError respostaErroHandlerException(MethodArgumentNotValidException e ) {

        List<FieldError> fieldErrors = e.getFieldErrors();

        List<ErrorField> list = fieldErrors.stream().map(fieldError -> new ErrorField(

                fieldError.getDefaultMessage(), fieldError.getField()
        )).toList();

        return new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação" , list);

    }

    @ExceptionHandler(DuplicateRecordException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError DuplicateRecordException(DuplicateRecordException e) {

        return ResponseError.conflict(e.getMessage());

    }

    @ExceptionHandler(OperationNotPermitted.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError OperationNotPermitted(OperationNotPermitted e) {

        return ResponseError.StandardResponse(e.getMessage());

    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseError  RequestTypeFailure(MethodNotAllowedException e) {

        return new ResponseError(HttpStatus.METHOD_NOT_ALLOWED.value(), "Falha no tipo de  requisição!", List.of());
    }


    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseError AccessDenied(AuthorizationDeniedException E) {
        return new ResponseError(HttpStatus.FORBIDDEN.value(), "Acesso Negado!", List.of());

    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError UnhandledError (RuntimeException e) {

        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ops! Ocorreu um erro desconhecido, entre em contato com o suporte." , List.of());

    }




}
