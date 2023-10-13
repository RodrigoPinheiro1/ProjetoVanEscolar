package com.van.vanescolarprojeto.Dto;

import com.van.vanescolarprojeto.Modelo.Endereco;
import com.van.vanescolarprojeto.Modelo.EstadoCivil;
import com.van.vanescolarprojeto.Modelo.StatusPedidoCorrida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsavelMotoristaDto {


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

    private List<AlunoDto> aluno;

    private MotoristaDto motorista;

}

