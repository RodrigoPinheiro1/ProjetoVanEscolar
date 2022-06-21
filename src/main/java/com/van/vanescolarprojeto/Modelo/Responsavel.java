package com.van.vanescolarprojeto.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Date dataNascimento;
    private String cpf;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private  EstadoCivil estadoCivil ;
    @JsonIgnore
    @ManyToOne
    private Motorista motorista;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Aluno> aluno = new ArrayList<>();


    public Responsavel() {
    }

    public Responsavel(String nome, Date dataNascimento, String cpf, EstadoCivil estadoCivil, String telefone) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.estadoCivil = estadoCivil;
        this.telefone = telefone;
    }

    public  void adicionar (Aluno aluno){

        List<Responsavel> responsavel = new ArrayList<>();

        aluno.setResponsavel(responsavel);
        this.aluno.add(aluno);
    }


}
