package com.van.vanescolarprojeto.service;

import com.van.vanescolarprojeto.Dto.MotoristaDto;
import com.van.vanescolarprojeto.Modelo.Automovel;
import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public MotoristaDto cadastrarMotorista(MotoristaDto motoristaDto) {

        Motorista motorista = modelMapper.map(motoristaDto, Motorista.class);
        Automovel automovel = modelMapper.map(motoristaDto.getAutomovel(), Automovel.class);

        motorista.setAutomovel(automovel);
        motoristaRepository.save(motorista);


        return modelMapper.map(motorista, MotoristaDto.class);
    }
}
