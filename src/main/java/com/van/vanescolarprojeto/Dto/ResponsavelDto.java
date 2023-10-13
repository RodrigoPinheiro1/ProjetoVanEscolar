package com.van.vanescolarprojeto.Dto;

import com.van.vanescolarprojeto.Modelo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @Valid
    private Endereco endereco;

    private StatusPedidoCorrida statusPedidoCorrida;

    @NotNull
    private EstadoCivil estadoCivil;

    @Valid
    @NotNull
    private List<AlunoDto> aluno = new ArrayList<>();
}
