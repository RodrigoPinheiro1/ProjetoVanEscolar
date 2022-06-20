package com.van.vanescolarprojeto.controler;

import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Repository.AutomovelRepository;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.controler.Dtos.Motorista.MotoristaDto;
import com.van.vanescolarprojeto.controler.Forms.Motorista.MotoristaAtualizarForm;
import com.van.vanescolarprojeto.controler.Forms.Motorista.MotoristaForm;
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
@RequestMapping("/motorista")
public class MotoristaController {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private AutomovelRepository automovelRepository;


    @GetMapping
    @Transactional
    public Page<MotoristaDto> listarNomeOuTudo(@RequestParam (required = false) String nome , //não obrigatorio.
                                                @PageableDefault(sort = "id", direction = Sort.Direction.ASC,
                                                        page = 0,size = 10)
                                               Pageable pageable) {

        if (nome == null) {
            Page<Motorista> motoristas = motoristaRepository.findAll(pageable);
            return MotoristaDto.converter(motoristas);
        }
        Page<Motorista> motoristas = motoristaRepository.findByNome(nome, pageable);

        return MotoristaDto.converter(motoristas);
    }



    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<MotoristaDto> listar(@PathVariable Long id) {
        Optional<Motorista> motorista = motoristaRepository.findById(id);
        if (motorista.isPresent()) {

            return ResponseEntity.ok(new MotoristaDto(motorista.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MotoristaDto> atualizar(@PathVariable Long id, @RequestBody @Valid
    MotoristaAtualizarForm motoristaAtualizarForm) {
        Optional<Motorista> motorista = motoristaRepository.findById(id);

        if (motorista.isPresent()) {

            motoristaAtualizarForm.atualizar(id, motoristaRepository);
            return ResponseEntity.ok(new MotoristaDto(motorista.get()));
        }

        return ResponseEntity.notFound().build();
    }



    @PostMapping
    @Transactional
    public ResponseEntity<MotoristaDto> cadastrar(@RequestBody @Valid MotoristaForm motoristaForm, UriComponentsBuilder uriComponentsBuilder) {

        Motorista motorista = motoristaForm.cadastrar();
        motoristaRepository.save(motorista);
        URI uri = uriComponentsBuilder.path("/motorista/{id}").buildAndExpand(motorista.getId()).toUri();
        return ResponseEntity.created(uri).body(new MotoristaDto(motorista));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {

        Optional<Motorista> motorista = motoristaRepository.findById(id);
        if (motorista.isPresent()) {
            motoristaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }


}
