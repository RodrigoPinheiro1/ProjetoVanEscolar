package com.van.vanescolarprojeto.controler.Forms.Motorista;

import com.van.vanescolarprojeto.Modelo.Automovel;
import com.van.vanescolarprojeto.Modelo.Motorista;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MotoristaForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String nome;
    @NotNull
    @NotEmpty
    @Length(min = 11)
    private String telefone;
    @NotNull
    @NotEmpty
    @Length(min = 11)
    private String cpf;
    @NotNull
    @NotEmpty
    private String cnh;
    private Date dataNascimento;

    private String senha;

    private String email;



    public Motorista cadastrar() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(senha);
        return new Motorista(nome, cpf, cnh, telefone, dataNascimento, encode, email);
    }
}
