package com.van.vanescolarprojeto.controler.Forms.Responsavel;

import com.van.vanescolarprojeto.Modelo.EstadoCivil;
import com.van.vanescolarprojeto.Modelo.Responsavel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class ResponsavelForm {


    @NotNull
    private String nome;
    @NotNull
    private Date dataNascimento;
    @NotNull
    private String cpf;

    @NotNull
    @NotEmpty
    private String telefone;

    @NotNull
    private String senha;

    @NotEmpty
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    public Responsavel cadastro() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(senha);

        return new Responsavel(nome, dataNascimento, cpf, telefone, encodedPassword, email, estadoCivil);
    }


}
