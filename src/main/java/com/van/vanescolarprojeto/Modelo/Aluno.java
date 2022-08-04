package com.van.vanescolarprojeto.Modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;

    private String telefone;

    @ManyToMany(mappedBy = "aluno",fetch = FetchType.LAZY)
    private List<Responsavel> responsavel = new ArrayList<>();

    public Aluno() {
    }

    public Aluno(String nome, LocalDate dataNascimento, String cpf, String telefone) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Aluno(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }
}

