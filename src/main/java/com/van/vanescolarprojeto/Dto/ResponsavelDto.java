package com.van.vanescolarprojeto.Dto;

import com.van.vanescolarprojeto.Modelo.Aluno;
import com.van.vanescolarprojeto.Modelo.EstadoCivil;
import com.van.vanescolarprojeto.Modelo.Motorista;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class ResponsavelDto {


    private Long id;
    @NotNull
    private String nome;
    private Date dataNascimento;
    @NotNull
    private String cpf;
    @NotNull
    private String telefone;

    @NotNull
    private EstadoCivil estadoCivil;

    @Valid
    @NotNull
    private List<Aluno> aluno = new ArrayList<>();
}
