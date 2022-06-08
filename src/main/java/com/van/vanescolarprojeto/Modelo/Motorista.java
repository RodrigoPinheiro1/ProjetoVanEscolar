package com.van.vanescolarprojeto.Modelo;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String cnh;
    private String telefone;
    private Date dataDeNascimento;

    @ManyToOne
    private Automovel automovel;

    @OneToOne
    private ContaSalario contaSalario;
    @OneToOne
    private ParceiroMotorista parceiroMotorista;

    @OneToMany(mappedBy = "motorista" ,fetch = FetchType.LAZY)
    private List<Responsavel> responsavel;


    public Motorista(String nome, String telefone, String cpf, Date dataNascimento, String cnh) {
    }

    public Motorista(String nome, String cpf, String telefone, Date dataDeNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataDeNascimento = dataDeNascimento;
    }
}
