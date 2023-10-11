package com.van.vanescolarprojeto.Dto;


import com.van.vanescolarprojeto.Modelo.Automovel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Access;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@AllArgsConstructor
public class MotoristaDto {

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

    @NotNull
    @NotBlank
    private String telefone;

    @NotNull
    @NotBlank
    private Date dataDeNascimento;

    @NotNull
    private AutomovelDto automovel;
}
