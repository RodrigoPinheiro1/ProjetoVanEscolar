package com.van.vanescolarprojeto.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String cnh;
    private String telefone;
    private Date dataDeNascimento;

    @OneToOne
    private Automovel automovel;

    @JsonIgnore
    @OneToOne
    private ContaSalario contaSalario;


    @OneToMany(mappedBy = "motorista")
    private List<Responsavel> responsavel = new ArrayList<>();


}
