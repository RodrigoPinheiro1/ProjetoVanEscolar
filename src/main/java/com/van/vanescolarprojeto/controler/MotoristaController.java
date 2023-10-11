package com.van.vanescolarprojeto.controler;

import com.van.vanescolarprojeto.Dto.MotoristaDto;
import com.van.vanescolarprojeto.service.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/motorista")

public class MotoristaController {


    @Autowired
    private MotoristaService motoristaService;


    @PostMapping
    public ResponseEntity<MotoristaDto> cadastrarMotorista(@RequestBody @Valid MotoristaDto motoristaDto,
                                                           UriComponentsBuilder uriComponentsBuilder) {


        MotoristaDto dto = motoristaService.cadastrarMotorista(motoristaDto);

        URI uri = uriComponentsBuilder.path("/motorista/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}



