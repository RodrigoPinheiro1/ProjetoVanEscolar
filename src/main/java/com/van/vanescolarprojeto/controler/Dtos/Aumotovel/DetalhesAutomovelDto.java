package com.van.vanescolarprojeto.controler.Dtos.Aumotovel;

import com.van.vanescolarprojeto.Modelo.Automovel;
import com.van.vanescolarprojeto.Modelo.Motorista;
import lombok.Getter;

@Getter
public class DetalhesAutomovelDto {


    private Long id;
    private String modelo;
    private String placa;



    public DetalhesAutomovelDto(Automovel automovel) {
        this.id = automovel.getId();
        this.modelo = automovel.getModelo();
        this.placa = automovel.getPlaca();

    }
}
