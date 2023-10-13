package com.van.vanescolarprojeto.Modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Responsavel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Date dataNascimento;
    private String cpf;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;


    @Enumerated(EnumType.STRING)
    private StatusPedidoCorrida statusPedidoCorrida;

    @ManyToOne
    private Motorista motorista;

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private List<Aluno> aluno = new ArrayList<>();



}
