package com.van.vanescolarprojeto.controler.Controllers;


import com.van.vanescolarprojeto.Modelo.Aluno;
import com.van.vanescolarprojeto.Repository.AlunoRepository;
import com.van.vanescolarprojeto.Repository.ResponsavelRepository;
import com.van.vanescolarprojeto.controler.Dtos.Aluno.AlunoDto;
import com.van.vanescolarprojeto.controler.Forms.Aluno.AlunoForm;
import com.van.vanescolarprojeto.controler.Forms.Aluno.AtualizarAlunoform;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/responsavel/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private ResponsavelRepository responsavelRepository;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<AlunoDto> cadastrarAluno(@PathVariable Long id, @RequestBody @Valid AlunoForm alunoForm
            , UriComponentsBuilder uriComponentsBuilder) {

        Aluno aluno = alunoForm.cadastrar(id, responsavelRepository);
        alunoRepository.save(aluno);

        URI uri = uriComponentsBuilder.path("/responsavel/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new AlunoDto(aluno));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AlunoDto> atualizarAluno(@PathVariable Long id, @RequestBody @Valid AtualizarAlunoform atualizarAlunoform) {

        Optional<Aluno> aluno = alunoRepository.findById(id);


        if (aluno.isPresent()) {
            atualizarAlunoform.atualizar(id, alunoRepository);
            return ResponseEntity.ok(new AlunoDto(aluno.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> excluirAluno(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);

        if (aluno.isPresent()) {
            alunoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }


}
