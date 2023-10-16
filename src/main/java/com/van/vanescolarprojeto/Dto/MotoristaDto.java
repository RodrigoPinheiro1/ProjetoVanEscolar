package com.van.vanescolarprojeto.Dto;


import com.van.vanescolarprojeto.Modelo.Endereco;
import com.van.vanescolarprojeto.Modelo.StatusPedidoCorrida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotoristaDto {

    private Long id;

    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    private String cpf;

    @NotNull
    @NotBlank
    private String cnh;
    private Endereco endereco;

    private StatusPedidoCorrida statusPedidoCorrida;


    @NotNull
    @NotBlank
    private String telefone;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date dataDeNascimento;

}
