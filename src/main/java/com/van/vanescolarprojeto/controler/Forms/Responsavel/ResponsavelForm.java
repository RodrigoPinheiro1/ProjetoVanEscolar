package com.van.vanescolarprojeto.controler.Forms.Responsavel;

import com.van.vanescolarprojeto.Modelo.EstadoCivil;
import com.van.vanescolarprojeto.Modelo.Responsavel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Date;

@Getter
public class ResponsavelForm {


    @NotNull
    private String nome;
    @NotNull
    private Date dataNascimento;
    @NotNull
    private String cpf;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;
    @NotNull
    @NotEmpty
    private String telefone;




    public Responsavel cadastro(){


        return new Responsavel(nome,dataNascimento,cpf,estadoCivil,telefone);
    }


}
