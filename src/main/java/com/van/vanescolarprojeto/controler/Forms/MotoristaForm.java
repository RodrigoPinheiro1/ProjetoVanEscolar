package com.van.vanescolarprojeto.controler.Forms;

import com.van.vanescolarprojeto.Modelo.Motorista;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MotoristaForm {

    private String nome ;
    private String telefone;
    private String cpf;
    private String cnh;
    private Date dataNascimento;






    public Motorista cadastrar() {

        return new Motorista(nome,telefone,cpf,dataNascimento,cnh);
    }
}
