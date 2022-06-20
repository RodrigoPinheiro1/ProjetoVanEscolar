package com.van.vanescolarprojeto.controler.Dtos.Responsavel;

import com.van.vanescolarprojeto.Modelo.EstadoCivil;
import com.van.vanescolarprojeto.Modelo.Responsavel;
import com.van.vanescolarprojeto.controler.Dtos.Aluno.AlunoDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class ResponsavelDto {

    private Long id;
    private String nome;
    private Date dataNascimento;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;





    public ResponsavelDto (Responsavel responsavel){

        this.id = responsavel.getId();
        this.nome = responsavel.getNome();
        this.dataNascimento = responsavel.getDataNascimento();
        this.cpf = responsavel.getCpf();
        estadoCivil = responsavel.getEstadoCivil();



    }


    public static Page<ResponsavelDto> converter(Page<Responsavel> responsavel) {


        return responsavel.map(ResponsavelDto::new);
    }
}
