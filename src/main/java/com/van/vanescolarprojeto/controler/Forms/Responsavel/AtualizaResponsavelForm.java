package com.van.vanescolarprojeto.controler.Forms.Responsavel;

import com.van.vanescolarprojeto.Modelo.EstadoCivil;
import com.van.vanescolarprojeto.Modelo.Responsavel;
import com.van.vanescolarprojeto.Repository.ResponsavelRepository;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AtualizaResponsavelForm {

    @NotNull
    private String nome;
    @NotNull
    @Length (min = 11, max = 11)
    private String telefone;
    @Enumerated (EnumType.STRING)
    private EstadoCivil estadoCivil;


    public Responsavel atualizar(Long id, ResponsavelRepository responsavelRepository) {

        Responsavel responsavel = responsavelRepository.getReferenceById(id);

        responsavel.setNome(nome);
        responsavel.setTelefone(telefone);
        responsavel.setEstadoCivil(estadoCivil);

        return responsavel;

    }
}
