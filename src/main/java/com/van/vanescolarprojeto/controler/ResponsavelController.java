package com.van.vanescolarprojeto.controler;


import com.van.vanescolarprojeto.Modelo.Responsavel;
import com.van.vanescolarprojeto.Repository.ResponsavelRepository;
import com.van.vanescolarprojeto.controler.Dtos.Responsavel.DetalhesResponsavel;
import com.van.vanescolarprojeto.controler.Dtos.Responsavel.ResponsavelDto;
import com.van.vanescolarprojeto.controler.Forms.Responsavel.AtualizaResponsavelForm;
import com.van.vanescolarprojeto.controler.Forms.Responsavel.ResponsavelForm;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/responsavel")
public class ResponsavelController {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @PostMapping
    public ResponseEntity<ResponsavelDto> cadastrar(@RequestBody @Valid ResponsavelForm responsavelForm,
                                                    UriComponentsBuilder uriComponentsBuilder) {

        Responsavel responsavel = responsavelForm.cadastro();
        responsavelRepository.save(responsavel);
        URI uri = uriComponentsBuilder.path("/responsavel/{id}").buildAndExpand(responsavel.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponsavelDto(responsavel));
    }

    @GetMapping
    public Page<ResponsavelDto> buscar (@RequestParam (required = false)  String nome,//não obrigatorio
                                        @PageableDefault (sort ="id" ,direction = Sort.Direction.DESC,
                                        page = 0,size = 10)  Pageable pageable) {

        if (nome == null){

            Page<Responsavel> responsavel = responsavelRepository.findAll(pageable);
            return ResponsavelDto.converter(responsavel);
        }
        Page<Responsavel> responsavel = responsavelRepository.findByNome(nome,pageable);
        return  ResponsavelDto.converter(responsavel);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesResponsavel> buscarPorId (@PathVariable Long id) {

        Optional<Responsavel> responsavel = responsavelRepository.findById(id);

        if (responsavel.isPresent()){

            return ResponseEntity.ok(new DetalhesResponsavel(responsavel.get()));

        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponsavelDto> atualizarResponsavel (@PathVariable Long id,@RequestBody @Valid
                                                                AtualizaResponsavelForm responsavelForm ){

        Optional<Responsavel> responsavel = responsavelRepository.findById(id);

        if (responsavel.isPresent()) {
            responsavelForm.atualizar(id, responsavelRepository);
            return ResponseEntity.ok(new ResponsavelDto(responsavel.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity <?> deletarResponsavel (@PathVariable Long id){

        Optional<Responsavel> responsavel = responsavelRepository.findById(id);

        if(responsavel.isPresent()){

            responsavelRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }


}
