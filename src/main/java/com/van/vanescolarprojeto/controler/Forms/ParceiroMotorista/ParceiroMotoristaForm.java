package com.van.vanescolarprojeto.controler.Forms.ParceiroMotorista;

import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Modelo.ParceiroMotorista;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ParceiroMotoristaForm {


    @NotNull
    private String nome;
    @NotEmpty
    private String telefone;
    private Date dataNascimento;
    private String cpf;


    public ParceiroMotorista cadastroComMotorista(Long id, MotoristaRepository motoristaRepository) {

        Motorista motorista = motoristaRepository.getReferenceById(id);
        ParceiroMotorista parceiroMotorista = new ParceiroMotorista(nome, telefone, dataNascimento, cpf);

        motorista.adicionar(parceiroMotorista);

        return parceiroMotorista;

    }

    public ParceiroMotorista cadastrar() {

        return new ParceiroMotorista(nome, telefone, dataNascimento, cpf);

    }
}
