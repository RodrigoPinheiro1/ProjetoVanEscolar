package com.van.vanescolarprojeto.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
