package com.van.vanescolarprojeto.controler.Dtos.Motorista;

import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.controler.Dtos.Aumotovel.DetalhesAutomovelDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class DetalhesMotoristaDto {


    private Long id;
    private String nome;
    private Date dataDeNascimento;
    private String telefone;
    private String cnh;

     private List<DetalhesAutomovelDto> automovel;


    public DetalhesMotoristaDto(Motorista motorista) {

        this.id = motorista.getId();
        this.nome = motorista.getNome();
        this.dataDeNascimento = motorista.getDataDeNascimento();
        this.telefone = motorista.getTelefone();
        this.cnh = motorista.getCnh();
        this.automovel = new ArrayList<>();
        automovel.addAll(motorista.getAutomovel().stream().map(DetalhesAutomovelDto::new).toList());


    }


    public static List<MotoristaDto> converter(Optional<Motorista> motorista, MotoristaRepository motoristaRepository, Long id) {

        motoristaRepository.getReferenceById(id);
        // return motorista.stream().map(MotoristaDto::new).collect(Collectors.toList());
        return motorista.stream().map(MotoristaDto::new).collect(Collectors.toList());

    }
}

