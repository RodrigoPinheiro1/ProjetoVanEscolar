package com.van.vanescolarprojeto.controler.Dtos.Aluno;

import com.van.vanescolarprojeto.Modelo.Aluno;
import lombok.Getter;

import java.util.Date;

@Getter
public class AlunoDto {


    private Long id;
    private String nome;
    private Date dataNascimento;
    private String cpf;


    public AlunoDto (Aluno aluno){

        this.id = aluno.getId();
        nome = aluno.getNome();
        dataNascimento = aluno.getDataNascimento();
        cpf = aluno.getCpf();

    }


}
