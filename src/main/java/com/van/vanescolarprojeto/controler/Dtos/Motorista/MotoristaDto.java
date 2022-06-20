package com.van.vanescolarprojeto.controler.Dtos.Motorista;

import com.van.vanescolarprojeto.Modelo.Motorista;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.Date;

@Getter
public class MotoristaDto {

    private Long id;
    private String nome;
    private Date dataDeNascimento;
    private String telefone;
    private String cnh;

   // private List<AutomovelDto> automovel;



    public MotoristaDto(Motorista motorista) {

        this.id = motorista.getId();
        this.nome = motorista.getNome();
        this.dataDeNascimento =motorista.getDataDeNascimento();
        this.telefone = motorista.getTelefone();
        this.cnh = motorista.getCnh();
     /*   this.automovel = new ArrayList<>();
        automovel.addAll(motorista.getAutomovel().stream().map(AutomovelDto::new).toList());

*/
    }


    public static Page<MotoristaDto> converter(Page<Motorista> motorista) {

       // return motorista.stream().map(MotoristaDto::new).collect(Collectors.toList());

        return motorista.map(MotoristaDto::new);

    }
}
