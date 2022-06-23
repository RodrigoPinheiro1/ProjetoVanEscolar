package com.van.vanescolarprojeto.controler.Dtos.ParceiroMotorista;

import com.van.vanescolarprojeto.Modelo.ParceiroMotorista;
import com.van.vanescolarprojeto.controler.Dtos.Motorista.MotoristaDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class DetalhesParceiroDto {

    private Long id;
    private String nome;
    private String telefone;
    private Date dataNascimento;
    private String cpf;

    private List<MotoristaDto> motorista;


    public DetalhesParceiroDto (ParceiroMotorista parceiroMotorista) {

        this.id = parceiroMotorista.getId();
        this.nome = parceiroMotorista.getNome();
        telefone = parceiroMotorista.getTelefone();
        dataNascimento = parceiroMotorista.getDataNascimento();
        cpf = parceiroMotorista.getCpf();
        motorista = new ArrayList<>();
        motorista.addAll(parceiroMotorista.getMotorista().stream().map(MotoristaDto::new).toList());

    }


    public static Page<ParceiroMotoristaDto> converter(Page<ParceiroMotorista> parceiroMotorista) {


        return parceiroMotorista.map(ParceiroMotoristaDto::new);
    }
}
