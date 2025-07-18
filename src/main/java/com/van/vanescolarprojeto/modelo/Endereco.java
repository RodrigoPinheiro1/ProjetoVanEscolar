package com.van.vanescolarprojeto.modelo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
