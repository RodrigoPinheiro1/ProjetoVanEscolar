package com.van.vanescolarprojeto.Modelo;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    private Motorista motorista;

    public Automovel()  {

    }
}
