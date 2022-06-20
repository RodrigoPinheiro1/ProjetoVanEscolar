package com.van.vanescolarprojeto.controler.Forms.Motorista;

import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
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
