package com.van.vanescolarprojeto.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Automovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private String placa;

    @JsonIgnore
    @ManyToOne
    private Motorista motorista;

    public Automovel()  {

    }

    public Automovel(String modelo, String placa) {
        this.modelo = modelo;
        this.placa = placa;
    }

    public void adicionar (Motorista motorista){

        motorista.getAutomovel();

    }


}
