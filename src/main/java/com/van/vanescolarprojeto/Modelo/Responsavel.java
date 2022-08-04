package com.van.vanescolarprojeto.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Responsavel extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;
    @JsonIgnore
    @ManyToOne
    private Motorista motorista;


    @ManyToMany
    private List<Aluno> aluno = new ArrayList<>();


    public Responsavel() {
    }

    public Responsavel(String nome, LocalDate dataNascimento, String cpf, String telefone, String senha, String email, EstadoCivil estadoCivil) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.telefone = telefone;
        this.senha = senha;
        this.email = email;
        this.estadoCivil = estadoCivil;
    }

    public void adicionar(Aluno aluno) {

        List<Responsavel> responsavel = new ArrayList<>();

        aluno.setResponsavel(responsavel);
        this.aluno.add(aluno);
    }


}
