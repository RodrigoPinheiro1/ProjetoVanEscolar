package com.van.vanescolarprojeto.controler;

import com.van.vanescolarprojeto.Dto.*;
import com.van.vanescolarprojeto.service.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {


    @Autowired
    private MotoristaService motoristaService;


    @PostMapping
    public ResponseEntity<MotoristaAutomovelDto> cadastrarMotorista(@RequestBody @Valid MotoristaAutomovelDto motoristaAutomovelDto,
                                                                    UriComponentsBuilder uriComponentsBuilder) {

        MotoristaAutomovelDto dto = motoristaService.cadastrarMotorista(motoristaAutomovelDto);

        URI uri = uriComponentsBuilder.path("/motorista/{id}").buildAndExpand(motoristaAutomovelDto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/pedidos/{idMotorista}")
    public Page<ResponsavelDto> verPedidosCorridas(@PathVariable Long idMotorista, Pageable pageable) {


        return motoristaService.verPedidosCorridas(idMotorista, pageable);
    }

    @PatchMapping("/aceitarCorrida/{idMotorista}")
    public ResponseEntity<ResponsavelMotoristaDto> aceitarCorrida(@PathVariable Long idMotorista,
                                                                  @RequestBody PedidoCorridaMotoristaDto pedidoCorridaMotoristaDto) {

        ResponsavelMotoristaDto dto = motoristaService.aceitarCorrida(idMotorista, pedidoCorridaMotoristaDto);


        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/negarCorrida/{idMotorista}")
    public ResponseEntity<ResponsavelMotoristaDto> negarCorrida(@PathVariable Long idMotorista,
                                                                  @RequestBody PedidoCorridaMotoristaDto pedidoCorridaMotoristaDto) {

        ResponsavelMotoristaDto dto = motoristaService.negarCorrida(idMotorista, pedidoCorridaMotoristaDto);


        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{idMotorista}")
    public ResponseEntity<AtualizaMotoristaDto> atualizarMotorista(@PathVariable Long idMotorista,
                                                                   @RequestBody @Valid AtualizaMotoristaDto motoristaAutomovelDto) {

        AtualizaMotoristaDto dto = motoristaService.atualizarMotorista(idMotorista,motoristaAutomovelDto);


        return ResponseEntity.ok(dto);
    }



    @GetMapping("/{idMotorista}")
    public ResponseEntity<MotoristaAutomovelDto> atualizarMotoristaAutomovel(@PathVariable Long idMotorista){

        MotoristaAutomovelDto id = motoristaService.findById(idMotorista);

        return ResponseEntity.ok(id);
    }



}



