package com.van.vanescolarprojeto.controler.Forms.ParceiroMotorista;

import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Modelo.ParceiroMotorista;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    private String email;
    private String senha;


    public ParceiroMotorista cadastroComMotorista(Long id, MotoristaRepository motoristaRepository) {
        Motorista motorista = motoristaRepository.getReferenceById(id);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(senha);
        ParceiroMotorista parceiroMotorista = new ParceiroMotorista(nome, telefone, dataNascimento, cpf, email,encode);

        motorista.adicionar(parceiroMotorista);

        return parceiroMotorista;

    }

    public ParceiroMotorista cadastrar() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(senha);
        return new ParceiroMotorista(nome, telefone, dataNascimento , cpf, email,encode);

    }
}
