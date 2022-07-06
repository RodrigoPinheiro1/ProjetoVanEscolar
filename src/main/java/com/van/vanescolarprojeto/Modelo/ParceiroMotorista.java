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
public class ParceiroMotorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private Date dataNascimento;
    private String cpf;
    private String senha ;

    private String email;


    @JsonIgnore
    @ManyToMany (targetEntity = Motorista.class,mappedBy = "parceiroMotorista", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Motorista> motorista = new ArrayList<>();


    public ParceiroMotorista(String nome, String telefone, Date dataNascimento, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    public ParceiroMotorista(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public ParceiroMotorista() {
    }


    public void remover(Motorista motorista) {


        this.motorista.add(motorista);
        motorista.getParceiroMotorista().remove(this);

    }

    public void adicionar(Motorista motorista) {
        List<ParceiroMotorista> parceiroMotorista = new ArrayList<>();

        motorista.setParceiroMotorista(parceiroMotorista);
        this.motorista.add(motorista);

    }
}
