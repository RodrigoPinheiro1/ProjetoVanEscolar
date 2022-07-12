package com.van.vanescolarprojeto.controler.Forms.Aluno;


import com.van.vanescolarprojeto.Modelo.Aluno;
import com.van.vanescolarprojeto.Repository.AlunoRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarAlunoform {

    private String nome;
    private String telefone;


    public Aluno atualizar(Long id, AlunoRepository alunoRepository) {

        Aluno aluno = alunoRepository.getReferenceById(id);

        aluno.setNome(nome);
        aluno.setTelefone(telefone);

        return aluno;
    }
}
