package com.van.vanescolarprojeto.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private ContaSalario contaSalario;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private ParceiroMotorista parceiroMotorista;

    @OneToMany(mappedBy = "motorista" ,fetch = FetchType.LAZY)
    private List<Responsavel>responsavel;


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


    public void adicionarAutomovel(Automovel automovel) {

        automovel.setMotorista(this);
        this.automovel.add(automovel);
    }

    public void adicionarResponsavel (Responsavel responsavel){

        responsavel.setMotorista(this);
        this.responsavel.add(responsavel);
    }

}
