package com.van.vanescolarprojeto.controler.Forms.ParceiroMotorista;

import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Modelo.ParceiroMotorista;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.Repository.ParceiroMotoristaRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoverVinculoForm {



    private Long idMotorista;




    public Motorista getIdMotorista (ParceiroMotoristaRepository parceiroMotoristaRepository, Long id){


        parceiroMotoristaRepository.getReferenceById(id);
        Motorista motorista = new Motorista();
        motorista.setId(idMotorista);
        parceiroMotoristaRepository.deletarVinculo(idMotorista, id);

        return motorista;
    }
}
