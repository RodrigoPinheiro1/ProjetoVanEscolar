package com.van.vanescolarprojeto.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDto {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;

    private String telefone;

}
