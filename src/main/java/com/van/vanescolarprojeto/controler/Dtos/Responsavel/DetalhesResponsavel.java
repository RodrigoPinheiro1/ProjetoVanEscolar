package com.van.vanescolarprojeto.controler.Dtos.Responsavel;

import com.van.vanescolarprojeto.Modelo.EstadoCivil;
import com.van.vanescolarprojeto.Modelo.Responsavel;
import com.van.vanescolarprojeto.controler.Dtos.Aluno.AlunoDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetalhesResponsavel {


    private Long id;
    private String nome;
    private Date dataNascimento;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    private List<AlunoDto> aluno;



    public DetalhesResponsavel (Responsavel responsavel){

        this.id = responsavel.getId();
        this.nome = responsavel.getNome();
        this.dataNascimento = responsavel.getDataNascimento();
        this.cpf = responsavel.getCpf();
        estadoCivil = responsavel.getEstadoCivil();
        this.aluno= new ArrayList<>();
        aluno.addAll(responsavel.getAluno().stream().map(AlunoDto::new).toList());


    }


}
