package com.van.vanescolarprojeto.controler.Dtos.Responsavel;

import com.van.vanescolarprojeto.Modelo.EstadoCivil;
import com.van.vanescolarprojeto.Modelo.Responsavel;
import lombok.Getter;
import org.springframework.data.domain.Page;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
public class ResponsavelDto {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;
    private String telefone;


    public ResponsavelDto(Responsavel responsavel) {

        this.id = responsavel.getId();
        this.nome = responsavel.getNome();
        this.dataNascimento = responsavel.getDataNascimento();
        this.cpf = responsavel.getCpf();
        this.telefone = responsavel.getTelefone();
        this.estadoCivil = responsavel.getEstadoCivil();
    }


    public static Page<ResponsavelDto> converter(Page<Responsavel> responsavel) {


        return responsavel.map(ResponsavelDto::new);
    }
}
