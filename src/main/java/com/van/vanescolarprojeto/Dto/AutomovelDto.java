package com.van.vanescolarprojeto.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AutomovelDto {

    private Long id;
    @NotBlank
    private String modelo;
    @NotBlank
    private String nomeCarro;

    @NotBlank
    private String placa;
}
