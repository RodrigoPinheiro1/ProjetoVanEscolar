package com.van.vanescolarprojeto.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Motorista extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String cnh;
    private String telefone;
    private Date dataDeNascimento;



    @OneToMany(mappedBy = "motorista", fetch = FetchType.LAZY)
    private List<Automovel> automovel = new ArrayList<>();

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private ContaSalario contaSalario;

    @JsonIgnore
    @ManyToMany
    private List<ParceiroMotorista> parceiroMotorista = new ArrayList<>();

    @OneToMany(mappedBy = "motorista", fetch = FetchType.LAZY)
    private List<Responsavel> responsavel = new ArrayList<>();


    public Motorista(String nome, String cpf, String cnh, String telefone, Date dataDeNascimento, String senha, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.telefone = telefone;
        this.dataDeNascimento = dataDeNascimento;
        this.senha = senha;
        this.email = email;
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

    public void adicionar(ParceiroMotorista parceiroMotorista) {
        List<Motorista> motorista = new ArrayList<>();

        parceiroMotorista.setMotorista(motorista);
        this.parceiroMotorista.add(parceiroMotorista); //adiciona na tabela do relacionamento many to many


    }



}
