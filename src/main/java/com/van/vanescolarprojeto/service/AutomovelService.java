package com.van.vanescolarprojeto.service;

import com.van.vanescolarprojeto.dto.MotoristaAutomovelDto;
import com.van.vanescolarprojeto.modelo.Motorista;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutomovelService {



    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public MotoristaAutomovelDto cadastrarMotorista(MotoristaAutomovelDto motoristaAutomovelDto) {

        Motorista motorista = modelMapper.map(motoristaAutomovelDto, Motorista.class);
        motoristaRepository.save(motorista);

        return modelMapper.map(motorista, MotoristaAutomovelDto.class);
    }

}
