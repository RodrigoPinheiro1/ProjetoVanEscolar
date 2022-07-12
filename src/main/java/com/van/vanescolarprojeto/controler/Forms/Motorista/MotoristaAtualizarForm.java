package com.van.vanescolarprojeto.controler.Forms.Motorista;

import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class MotoristaAtualizarForm {
    @NotEmpty
    @NotNull
    private String nome;
    @NotEmpty
    @NotNull
    @Length(min = 11)
    private String telefone;


    public Motorista atualizar(Long id, MotoristaRepository motoristaRepository) {

        Motorista motorista = motoristaRepository.getReferenceById(id);

        motorista.setNome(nome);
        motorista.setTelefone(this.telefone);

        return motorista;
    }
}
