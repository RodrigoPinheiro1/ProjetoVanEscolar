package com.van.vanescolarprojeto.Modelo;

import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private  EstadoCivil estadoCivil ;

    @ManyToOne
    private Motorista motorista;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Aluno> aluno = new ArrayList<>();


    public Responsavel() {
    }

    public Responsavel(String nome, Date dataNascimento, String cpf, EstadoCivil estadoCivil) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.estadoCivil = estadoCivil;
    }
}
