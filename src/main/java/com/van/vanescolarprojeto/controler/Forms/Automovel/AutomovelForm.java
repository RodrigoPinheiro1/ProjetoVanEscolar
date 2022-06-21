package com.van.vanescolarprojeto.controler.Forms.Automovel;

import com.van.vanescolarprojeto.Modelo.Automovel;
import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AutomovelForm {

    @NotNull
    @NotEmpty
    private String modelo;
    @NotNull
    @NotEmpty
    private String placa;



    public Automovel cadastrar(MotoristaRepository motoristaRepository, Long id) {

        Motorista motorista = motoristaRepository.getReferenceById(id);
        Automovel automovel = new Automovel(modelo, placa);
        motorista.adicionarAutomovel(automovel);


        return (automovel);
    }


}
