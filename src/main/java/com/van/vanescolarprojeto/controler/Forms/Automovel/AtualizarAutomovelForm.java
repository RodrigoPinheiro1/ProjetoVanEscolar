package com.van.vanescolarprojeto.controler.Forms.Automovel;

import com.van.vanescolarprojeto.Modelo.Automovel;
import com.van.vanescolarprojeto.Repository.AutomovelRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.lang.management.LockInfo;

@Setter
@Getter
public class AtualizarAutomovelForm {

    @NotEmpty
    private String modelo;

    @NotEmpty
    private String placa;


    public Automovel atualizar(Long id, AutomovelRepository automovelRepository) {

        Automovel automovel = automovelRepository.getReferenceById(id);

        automovel.setModelo(modelo);
        automovel.setPlaca(placa);

        return automovel;
    }


}
