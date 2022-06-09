package com.van.vanescolarprojeto.controler.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {


    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST) //devolve uma badRequest
    @ExceptionHandler
    public List<ErroDeFormularioDto> handle (MethodArgumentNotValidException exception){

        List<ErroDeFormularioDto> erroDeFormularioDtos = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors(); // getBindingResult faz uma vinculação com o erro
        // get field erro pega qualquer erro que estier no primeiro
        fieldErrors.forEach(fieldError -> {                    //localeContext serve para ver a posicção atual da list
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            ErroDeFormularioDto erro = new ErroDeFormularioDto(fieldError.getField(),mensagem);
            erroDeFormularioDtos.add(erro);
        });

        return erroDeFormularioDtos;

    }


}
