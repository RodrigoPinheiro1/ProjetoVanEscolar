package com.van.vanescolarprojeto.Dto;


import com.van.vanescolarprojeto.Modelo.Endereco;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotoristaAutomovelDto {

    private Long id;

    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    private String cpf;

    @NotNull
    @NotBlank
    private String cnh;

    @Valid
    @NotNull
    private Endereco endereco;

    @NotNull
    @NotBlank
    private String telefone;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date dataDeNascimento;

    @NotNull
    @Valid
    private AutomovelDto automovel;
}
