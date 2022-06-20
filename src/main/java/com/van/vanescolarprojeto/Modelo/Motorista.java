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

    @OneToMany(mappedBy = "motorista",fetch = FetchType.LAZY)
    private List<Automovel> automovel;

    @OneToOne(fetch = FetchType.LAZY)
    private ContaSalario contaSalario;
    @OneToOne(fetch = FetchType.LAZY)
    private ParceiroMotorista parceiroMotorista;

    @OneToMany(mappedBy = "motorista" ,fetch = FetchType.LAZY)
    private List<Responsavel> responsavel;


    public Motorista(String nome, String cpf, String cnh, String telefone, Date dataDeNascimento) {

        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.telefone = telefone;
        this.dataDeNascimento = dataDeNascimento;

    }

    public Motorista() {
    }

    public Motorista(String nome, String telefone, Date dataDeNascimento, ParceiroMotorista parceiroMotorista) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataDeNascimento = dataDeNascimento;
        this.parceiroMotorista = parceiroMotorista;
    }


    public void adicionar(Automovel automovel) {

        automovel.setMotorista(this);
        this.automovel.add(automovel);
    }

}
