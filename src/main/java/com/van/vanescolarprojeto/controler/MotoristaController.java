package com.van.vanescolarprojeto.controler;

import com.van.vanescolarprojeto.Modelo.Automovel;
import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Repository.AutomovelRepository;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.controler.Dtos.MotoristaDto;
import com.van.vanescolarprojeto.controler.Forms.MotoristaForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private AutomovelRepository automovelRepository;


    @GetMapping
    public List<MotoristaDto> listarTodos() {


        List<Motorista> motoristas = motoristaRepository.findAll();
        return MotoristaDto.converter(motoristas) ;
    }

    @PostMapping
    public ResponseEntity<MotoristaDto>  cadastrar (@RequestBody @Valid MotoristaForm motoristaForm , UriComponentsBuilder uriComponentsBuilder) {

        Motorista motorista = motoristaForm.cadastrar();
        motoristaRepository.save(motorista);
        automovelRepository.save(new Automovel());

        URI uri  =uriComponentsBuilder.path("/motorista/{id}").buildAndExpand(motorista.getId()).toUri();
        return ResponseEntity.created(uri).body(new MotoristaDto(motorista));
    }







}
