package com.van.vanescolarprojeto.controler.Dtos;

import com.van.vanescolarprojeto.Modelo.Automovel;
import com.van.vanescolarprojeto.Modelo.Motorista;
import lombok.Getter;
import org.hibernate.hql.spi.id.cte.CteValuesListDeleteHandlerImpl;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MotoristaDto {

    private Long id;
    private String nome;
    private Date dataDeNascimento;
    private String telefone;

    private Automovel automovel;



    public MotoristaDto(Motorista motorista) {

        this.id = motorista.getId();
        this.nome = motorista.getNome();
        this.dataDeNascimento =motorista.getDataDeNascimento();
        this.telefone = motorista.getTelefone();
        this.automovel = motorista.getAutomovel();


    }


    public static List<MotoristaDto> converter(List<Motorista> motorista) {

        return motorista.stream().map(MotoristaDto::new).collect(Collectors.toList());

    }
}
