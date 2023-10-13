package com.van.vanescolarprojeto.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {



    private String cep;
    @NotNull
    @NotBlank
    private String cidade;
    @NotNull
    @NotBlank
    private String numero;
    @NotNull
    @NotBlank
    private String logradouro;
    @NotBlank
    @NotNull
    private String complemento;

    @NotBlank
    @NotNull
    private String bairro;
    @NotNull
    @NotBlank
    private String localidade;


}
