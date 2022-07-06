package com.van.vanescolarprojeto.controler.Forms.ParceiroMotorista;

import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Modelo.ParceiroMotorista;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.Repository.ParceiroMotoristaRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarParceiroForm {


    private Long idMotorista;


    public ParceiroMotorista atualizarVinculo(Long idParceiroMotorista, ParceiroMotoristaRepository parceiroMotoristaRepository,
                                              MotoristaRepository motoristaRepository) {

        ParceiroMotorista parceiroMotorista = parceiroMotoristaRepository.getReferenceById(idParceiroMotorista);
        Motorista motorista = motoristaRepository.getReferenceById(idMotorista);

        motorista.setId(idMotorista);
        motorista.adicionar(parceiroMotorista);

        return parceiroMotorista;
    }


}
