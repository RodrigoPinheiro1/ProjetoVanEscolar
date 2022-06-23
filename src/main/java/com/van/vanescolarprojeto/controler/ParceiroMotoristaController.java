package com.van.vanescolarprojeto.controler;


import com.van.vanescolarprojeto.Modelo.ParceiroMotorista;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.Repository.ParceiroMotoristaRepository;
import com.van.vanescolarprojeto.controler.Dtos.ParceiroMotorista.DetalhesParceiroDto;
import com.van.vanescolarprojeto.controler.Dtos.ParceiroMotorista.ParceiroMotoristaDto;
import com.van.vanescolarprojeto.controler.ParceiroMotorista.ParceiroMotoristaForm;
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

@RestController
@RequestMapping("/parceiroMotorista")
public class ParceiroMotoristaController {

    @Autowired
    private ParceiroMotoristaRepository parceiroMotoristaRepository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesParceiroDto> cadastrarParceiro( @PathVariable Long id,
                                                                  @RequestBody @Valid ParceiroMotoristaForm parceiroMotoristaForm,
                                                                 UriComponentsBuilder uriComponentsBuilder) {

        ParceiroMotorista parceiroMotorista = parceiroMotoristaForm.cadastro(id,motoristaRepository);
        parceiroMotoristaRepository.save(parceiroMotorista);

        URI uri = uriComponentsBuilder.path("/parceiroMotorista/{id}").buildAndExpand(parceiroMotorista.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesParceiroDto(parceiroMotorista));
    }

    @GetMapping
    @Transactional
    public Page<ParceiroMotoristaDto> paginacaoParceiro (@RequestParam (required = false) String nome,
                                                                   @PageableDefault (sort = "id",
                                                                           direction = Sort.Direction.ASC,
                                                                   page = 0, size = 10) Pageable pageable){

        if (nome == null) {
            Page<ParceiroMotorista> parceiroMotorista = parceiroMotoristaRepository.findAll(pageable);
            return  ParceiroMotoristaDto.converter(parceiroMotorista);
        }
        Page<ParceiroMotorista> parceiroMotorista = parceiroMotoristaRepository.findByNome(nome,pageable);
        return ParceiroMotoristaDto.converter(parceiroMotorista);
    }





}
