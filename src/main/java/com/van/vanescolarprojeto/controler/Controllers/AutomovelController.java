package com.van.vanescolarprojeto.controler.Controllers;

import com.van.vanescolarprojeto.Modelo.Automovel;
import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Repository.AutomovelRepository;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.controler.Dtos.Aumotovel.DetalhesAutomovelDto;
import com.van.vanescolarprojeto.controler.Dtos.Motorista.DetalhesMotoristaDto;
import com.van.vanescolarprojeto.controler.Forms.Automovel.AtualizarAutomovelForm;
import com.van.vanescolarprojeto.controler.Forms.Automovel.AutomovelForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "motorista/automoveis")
public class AutomovelController {

    @Autowired
    private AutomovelRepository automovelRepository;
    @Autowired
    private MotoristaRepository motoristaRepository;


    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesAutomovelDto> cadastrar(@PathVariable Long id,
                                                          @RequestBody @Valid AutomovelForm automovelForm,
                                                          UriComponentsBuilder uriComponentsBuilder) {
        Automovel automovel = automovelForm.cadastrar(motoristaRepository, id);
       automovelRepository.save(automovel);

        URI uri = uriComponentsBuilder.path("/automoveis/{id}").buildAndExpand(automovel.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesAutomovelDto(automovel));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesMotoristaDto> ConsultaDetalhes(@PathVariable Long id) {

        Optional<Motorista> motorista = motoristaRepository.findById(id);

        if (motorista.isPresent()) {

            return ResponseEntity.ok(new DetalhesMotoristaDto(motorista.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> deletarAutomovel(@PathVariable Long id) {

        Optional<Automovel> automovel = automovelRepository.findById(id);

        if (automovel.isPresent()) {

            automovelRepository.deleteById(id);
            return ResponseEntity.ok().build();

        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesAutomovelDto> atualizarAutomovel(@PathVariable Long id,
                                                                   @RequestBody @Valid AtualizarAutomovelForm
                                                                           atualizarAutomovelForm) {

        Optional<Automovel> automovel = automovelRepository.findById(id);

        if (automovel.isPresent()) {
            atualizarAutomovelForm.atualizar(id, automovelRepository);
            return ResponseEntity.ok(new DetalhesAutomovelDto(automovel.get()));
        }

        return ResponseEntity.notFound().build();
    }

}
