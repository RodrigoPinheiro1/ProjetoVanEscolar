package com.van.vanescolarprojeto.utils;

import com.van.vanescolarprojeto.dto.AtualizaMotoristaDto;
import com.van.vanescolarprojeto.dto.MotoristaAutomovelDto;
import com.van.vanescolarprojeto.dto.MotoristaDto;
import com.van.vanescolarprojeto.modelo.Automovel;
import com.van.vanescolarprojeto.modelo.Motorista;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperMotoristaUtils {

    private final ModelMapper modelMapper;

    public Motorista motoristaAutomovelDTOtoMotorista(MotoristaAutomovelDto motoristaAutomovelDto) {

        return modelMapper.map(motoristaAutomovelDto, Motorista.class);

    }

    public Automovel automovelDTOtoAutomovel(@NotNull @Valid MotoristaAutomovelDto automovel) {
        return modelMapper.map(automovel.getAutomovel(), Automovel.class);
    }

    public MotoristaAutomovelDto motoristaToMotoristaAutomovelDTO(Motorista motorista) {


        return modelMapper.map(motorista, MotoristaAutomovelDto.class);
    }

    public MotoristaDto motoristaToMotoristaDTO(Motorista motorista) {

        return modelMapper.map(motorista, MotoristaDto.class);
    }
    public Motorista atualizaMotoristaDtoToMotorista(AtualizaMotoristaDto motoristaDto) {

        return modelMapper.map(motoristaDto, Motorista.class);
    }

    public AtualizaMotoristaDto motoristaToAtualizaMotoristaDTO(Motorista motorista) {

        return modelMapper.map(motorista, AtualizaMotoristaDto.class);

    }
}
