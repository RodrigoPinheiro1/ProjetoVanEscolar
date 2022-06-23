package com.van.vanescolarprojeto.controler.ParceiroMotorista;

import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Modelo.ParceiroMotorista;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.Repository.ParceiroMotoristaRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.lang.management.LockInfo;
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


    public ParceiroMotorista cadastro(Long id, MotoristaRepository motoristaRepository) {

        Motorista motorista = motoristaRepository.getReferenceById(id);
        ParceiroMotorista parceiroMotorista = new ParceiroMotorista(nome, telefone, dataNascimento, cpf);

        motorista.adicionar(parceiroMotorista);

        return parceiroMotorista;

    }
}
