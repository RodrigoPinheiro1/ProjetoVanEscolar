package com.van.vanescolarprojeto.controler;


import com.van.vanescolarprojeto.Dto.ResponsavelDto;
import com.van.vanescolarprojeto.service.ResponsavelService;
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
@RequestMapping("/responsavel")
public class ResponsavelController {

    @Autowired
    private ResponsavelService responsavelService;

    @PostMapping
    public ResponseEntity<ResponsavelDto> cadastrar(@RequestBody @Valid ResponsavelDto dto,
                                                    UriComponentsBuilder uriComponentsBuilder) {


        responsavelService.cadastrarResponsavel(dto);
        URI uri = uriComponentsBuilder.path("/responsavel/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }


}
