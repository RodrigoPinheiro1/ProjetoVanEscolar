package com.van.vanescolarprojeto.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AutomovelDto {

    private Long id;
    private String modelo;
    private String nomeCarro;

    private String placa;
}
