package com.van.vanescolarprojeto.controler;


import com.van.vanescolarprojeto.dto.*;
import com.van.vanescolarprojeto.service.MotoristaService;
import com.van.vanescolarprojeto.service.ResponsavelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;

import java.net.URI;

@RestController
@RequestMapping("/responsaveis")
@RequiredArgsConstructor
public class ResponsavelController {

    private final ResponsavelService responsavelService;

    private final MotoristaService motoristaService;

    @PostMapping
    public ResponseEntity<ResponsavelDto> cadastrarResponsavel(@RequestBody @Valid ResponsavelDto dto,
                                                               UriComponentsBuilder uriComponentsBuilder) {


        ResponsavelDto responsavelDto = responsavelService.cadastrarResponsavel(dto);
        URI uri = uriComponentsBuilder.path("/responsavel/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(responsavelDto);
    }

    @GetMapping("/motoristas")
    public Page<MotoristaDto> procurarMotorista(@RequestParam String cidade,
                                                @RequestParam(required = false) String bairro,
                                                Pageable pageable) {

        return motoristaService.acharMotorista(cidade, bairro, pageable);

    }

    @PatchMapping("/pedido/{idResponsavel}")
    public ResponseEntity<ResponsavelMotoristaDto> pedirCorridas(@PathVariable Long idResponsavel,
                                                                 @RequestBody @Valid PedidoCorridaResponsavelDto pedidoCorrida) {

        ResponsavelMotoristaDto responsavelMotoristaDto = responsavelService.solicitarCorrida(idResponsavel, pedidoCorrida);

        return ResponseEntity.ok(responsavelMotoristaDto);

    }

    @GetMapping("/{id}")
    public ResponsavelMotoristaDto procurarMotorista(@PathVariable Long id) {

        return responsavelService.findByid(id);

    }


}