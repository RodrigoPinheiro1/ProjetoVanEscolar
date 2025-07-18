package com.van.vanescolarprojeto.dto;

import com.van.vanescolarprojeto.modelo.Endereco;
import com.van.vanescolarprojeto.modelo.EstadoCivil;
import com.van.vanescolarprojeto.modelo.StatusPedidoCorrida;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
