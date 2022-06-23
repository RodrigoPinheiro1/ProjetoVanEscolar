package com.van.vanescolarprojeto.controler.Dtos.ParceiroMotorista;

import com.van.vanescolarprojeto.Modelo.ParceiroMotorista;
import com.van.vanescolarprojeto.controler.Dtos.Motorista.MotoristaDto;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class ParceiroMotoristaDto {


    private Long id;
    private String nome;
    private String telefone;
    private Date dataNascimento;
    private String cpf;




    public ParceiroMotoristaDto (ParceiroMotorista parceiroMotorista) {

        this.id = parceiroMotorista.getId();
        this.nome = parceiroMotorista.getNome();
        telefone = parceiroMotorista.getTelefone();
        dataNascimento = parceiroMotorista.getDataNascimento();
        cpf = parceiroMotorista.getCpf();

    }


    public static Page<ParceiroMotoristaDto> converter(Page<ParceiroMotorista> parceiroMotorista) {


        return parceiroMotorista.map(ParceiroMotoristaDto::new);
    }
}
