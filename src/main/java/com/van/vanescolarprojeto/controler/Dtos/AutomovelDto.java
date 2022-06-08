package com.van.vanescolarprojeto.controler.Dtos;

import com.van.vanescolarprojeto.Modelo.Automovel;
import lombok.Getter;

@Getter
public class AutomovelDto {


    private Long id;
    private String modelo;
    private String placa;


    public AutomovelDto(Automovel automovel) {
        this.id = automovel.getId();
        this.modelo = automovel.getModelo();
        this.placa = automovel.getPlaca();
    }
}
