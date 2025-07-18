package com.van.vanescolarprojeto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalMessageException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, HandlerMethodValidationException.class})
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
                HttpStatus.NOT_FOUND.value(), "usuario não encontrado"));

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<MessageError> urlNaoEncontrada(){

        return ResponseEntity.badRequest().body(new MessageError(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(), "Url não existe"));

    }


}
