package com.van.vanescolarprojeto.controler.Forms.Aluno;

import com.van.vanescolarprojeto.Modelo.Aluno;
import com.van.vanescolarprojeto.Modelo.Responsavel;
import com.van.vanescolarprojeto.Repository.ResponsavelRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
public class AlunoForm {


    @NotEmpty
    private String nome;

    private LocalDate dataNascimento;
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
