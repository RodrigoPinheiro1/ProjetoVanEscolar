package com.van.vanescolarprojeto.dto;


import com.van.vanescolarprojeto.modelo.Endereco;
import com.van.vanescolarprojeto.modelo.StatusPedidoCorrida;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
