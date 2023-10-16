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

    @OneToOne(cascade = CascadeType.ALL)
    private Automovel automovel;

    @Enumerated(EnumType.STRING)
    private StatusPedidoCorrida statusPedidoCorrida;


    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "motorista", cascade = CascadeType.ALL)
    private List<Responsavel> responsavel = new ArrayList<>();





}
