package com.van.vanescolarprojeto.service;

import com.van.vanescolarprojeto.Dto.MotoristaDto;
import com.van.vanescolarprojeto.Dto.ResponsavelDto;
import com.van.vanescolarprojeto.Modelo.Aluno;
import com.van.vanescolarprojeto.Modelo.Responsavel;
import com.van.vanescolarprojeto.Repository.ResponsavelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsavelService {


    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponsavelDto cadastrarResponsavel(ResponsavelDto dto) {

        Responsavel responsavel = modelMapper.map(dto, Responsavel.class);


        responsavel.getAluno().forEach(aluno -> aluno.setResponsavel(responsavel));

        responsavelRepository.save(responsavel);

        return modelMapper.map(responsavel, ResponsavelDto.class);
    }

}
