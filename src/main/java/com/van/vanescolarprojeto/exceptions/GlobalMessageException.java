package com.van.vanescolarprojeto.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;


@RestControllerAdvice
public class GlobalMessageException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageError> validacaoCampos(MethodArgumentNotValidException exception) {


        var erros = exception.getFieldErrors();

        List<ErrorValidation> errorValidations = erros.stream().map(ErrorValidation::new).toList();

        return ResponseEntity.badRequest().body(new MessageError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), "error fields", errorValidations));

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsuarioNaoEncontrado.class)
    public ResponseEntity<MessageError> usuarioNaoEncontrado(){

        return ResponseEntity.badRequest().body(new MessageError(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(), "nao encontrado"));

    }


}
