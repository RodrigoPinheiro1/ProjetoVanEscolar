package com.van.vanescolarprojeto.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

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

    @ManyToOne
    private Motorista motorista;


    @OneToMany(mappedBy = "responsavel",cascade = CascadeType.ALL)
    private List<Aluno> aluno = new ArrayList<>();


}
