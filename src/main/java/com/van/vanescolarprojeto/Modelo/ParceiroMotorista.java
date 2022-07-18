package com.van.vanescolarprojeto.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
public class ParceiroMotorista extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private Date dataNascimento;
    private String cpf;


    @JsonIgnore
    @ManyToMany(targetEntity = Motorista.class, mappedBy = "parceiroMotorista", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Motorista> motorista = new ArrayList<>();


        public ParceiroMotorista(String nome, String telefone, Date dataNascimento, String cpf,String email, String senha) {
            this.nome = nome;
            this.telefone = telefone;
            this.dataNascimento = dataNascimento;
            this.cpf = cpf;
            this.email= email;
            this.senha = senha;
        }

    public ParceiroMotorista(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public ParceiroMotorista() {
    }

    public void adicionar(Motorista motorista) {
        List<ParceiroMotorista> parceiroMotorista = new ArrayList<>();

        motorista.setParceiroMotorista(parceiroMotorista);
        this.motorista.add(motorista);

    }

}
