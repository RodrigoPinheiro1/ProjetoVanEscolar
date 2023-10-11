package com.van.vanescolarprojeto.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AutomovelDto {

    private Long id;
    private String modelo;
    private String nomeCarro;

    private String placa;
}
