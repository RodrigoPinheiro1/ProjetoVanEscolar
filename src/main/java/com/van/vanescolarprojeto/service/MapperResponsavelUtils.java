package com.van.vanescolarprojeto.service;

import com.van.vanescolarprojeto.dto.ResponsavelDto;
import com.van.vanescolarprojeto.dto.ResponsavelMotoristaDto;
import com.van.vanescolarprojeto.modelo.Responsavel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperResponsavelUtils {

    private final ModelMapper modelMapper;


    public ResponsavelDto responsavelToResponsavelDTO(Responsavel responsavel) {

        return modelMapper.map(responsavel, ResponsavelDto.class);

    }

    public ResponsavelMotoristaDto responsavelToResponsavelMotoristaDTO(Responsavel responsavel) {


        return modelMapper.map(responsavel, ResponsavelMotoristaDto.class);

    }
}
