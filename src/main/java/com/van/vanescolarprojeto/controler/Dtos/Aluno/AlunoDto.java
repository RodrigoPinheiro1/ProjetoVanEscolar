package com.van.vanescolarprojeto.controler.Dtos.Aluno;

import com.van.vanescolarprojeto.Modelo.Aluno;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AlunoDto {


    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String telefone;


    public AlunoDto (Aluno aluno){

        this.id = aluno.getId();
        nome = aluno.getNome();
        dataNascimento = aluno.getDataNascimento();
        cpf = aluno.getCpf();
        telefone = aluno.getTelefone();

    }


}
