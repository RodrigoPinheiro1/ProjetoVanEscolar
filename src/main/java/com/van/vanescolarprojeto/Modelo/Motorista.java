package com.van.vanescolarprojeto.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String cnh;
    private String telefone;
    private Date dataDeNascimento;

    private String senha ;

    private String email;

    @OneToMany(mappedBy = "motorista",fetch = FetchType.LAZY)
    private List<Automovel> automovel;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private ContaSalario contaSalario;

    @JsonIgnore
    @ManyToMany
    private List<ParceiroMotorista> parceiroMotorista = new ArrayList<>();

    @OneToMany(mappedBy = "motorista" ,fetch = FetchType.LAZY)
    private List<Responsavel>responsavel = new ArrayList<>();


    public Motorista(String nome, String cpf, String cnh, String telefone, Date dataDeNascimento) {

        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.telefone = telefone;
        this.dataDeNascimento = dataDeNascimento;

    }

    public Motorista() {
    }

    public Motorista(String nome, String telefone, Date dataDeNascimento, List<ParceiroMotorista> parceiroMotorista) {
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

    public void adicionar(ParceiroMotorista parceiroMotorista) {
        List<Motorista> motorista = new ArrayList<>();

        parceiroMotorista.setMotorista(motorista);
        this.parceiroMotorista.add(parceiroMotorista); //adiciona na tabela do relacionamento many to many


    }

    public void remover(ParceiroMotorista parceiroMotorista) {


        this.parceiroMotorista.add(parceiroMotorista);
        parceiroMotorista.getMotorista().remove(this);


    }
}
