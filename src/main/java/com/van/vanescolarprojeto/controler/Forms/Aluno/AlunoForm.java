package com.van.vanescolarprojeto.controler.Forms.Aluno;

import com.van.vanescolarprojeto.Modelo.Aluno;
import com.van.vanescolarprojeto.Modelo.Responsavel;
import com.van.vanescolarprojeto.Repository.ResponsavelRepository;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AlunoForm {


    @NotEmpty
    private String nome;

    private Date dataNascimento;
    @NotEmpty
    private String cpf;
    private String telefone;



    public Aluno cadastrar(Long id, ResponsavelRepository responsavelRepository) {

        Responsavel responsavel = responsavelRepository.getReferenceById(id);
        Aluno aluno = new Aluno(nome,dataNascimento,cpf,telefone);
        responsavel.adicionar(aluno);

        return aluno;
    }
}
