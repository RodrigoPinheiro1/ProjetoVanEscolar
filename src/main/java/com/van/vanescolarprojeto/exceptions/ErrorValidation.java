package com.van.vanescolarprojeto.exceptions;

import org.springframework.validation.FieldError;


public record ErrorValidation (String campo, String mensagem) {


    public ErrorValidation(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}
