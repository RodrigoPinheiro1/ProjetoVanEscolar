package com.van.vanescolarprojeto.controler.Forms;

import com.van.vanescolarprojeto.Modelo.Automovel;
import com.van.vanescolarprojeto.Modelo.Motorista;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MotoristaForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String nome ;
    @NotNull @NotEmpty @Length(min = 11)
    private String telefone;
    @NotNull @NotEmpty @Length(min = 11)
    private String cpf;
    @NotNull @NotEmpty
    private String cnh;
    private Date dataNascimento;

    private List<Automovel> automovel= new ArrayList<>();




    public Motorista cadastrar() {


        return new Motorista(nome,cpf,cnh,telefone,dataNascimento);
    }
}
