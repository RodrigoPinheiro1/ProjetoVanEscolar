package com.van.vanescolarprojeto.controler;

import com.van.vanescolarprojeto.dto.*;
import com.van.vanescolarprojeto.service.MotoristaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/motoristas")
@RequiredArgsConstructor
@Validated
public class MotoristaController {


    private final MotoristaService motoristaService;


    @PostMapping
    public ResponseEntity<MotoristaAutomovelDto> cadastrarMotorista(@RequestBody @Valid
                                                                    @NotNull MotoristaAutomovelDto motoristaAutomovelDto) {

        MotoristaAutomovelDto dto = motoristaService.cadastrarMotorista(motoristaAutomovelDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/pedidos/{idMotorista}")
    public Page<ResponsavelDto> verPedidosCorridas(@PathVariable @NotNull Long idMotorista, Pageable pageable) {

        return motoristaService.verPedidosCorridas(idMotorista, pageable);
    }

    @PatchMapping("/aceita-corridas/{idMotorista}")
    public ResponseEntity<ResponsavelMotoristaDto> aceitarCorrida(@PathVariable @NotNull Long idMotorista,
                                                                  @RequestBody @NotNull @Valid PedidoCorridaMotoristaDto pedidoCorridaMotoristaDto) {

        ResponsavelMotoristaDto dto = motoristaService.aceitarCorrida(idMotorista, pedidoCorridaMotoristaDto);


        return ResponseEntity.ok(dto);
    }


    @GetMapping("/motoristas")
    public Page<MotoristaDto> procurarMotorista(@RequestParam String cidade,
                                                @RequestParam(required = false) String bairro,
                                                Pageable pageable) {

        return motoristaService.acharMotorista(cidade, bairro, pageable);

    }

    @PatchMapping("/nega-corridas/{idMotorista}")
    public ResponseEntity<ResponsavelMotoristaDto> negarCorrida(@PathVariable @NotNull Long idMotorista,
                                                                @RequestBody PedidoCorridaMotoristaDto pedidoCorridaMotoristaDto) {

        ResponsavelMotoristaDto dto = motoristaService.negarCorrida(idMotorista, pedidoCorridaMotoristaDto);


        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{idMotorista}")
    public ResponseEntity<AtualizaMotoristaDto> atualizarMotorista(@PathVariable @NotNull Long idMotorista,
                                                                   @RequestBody @Valid AtualizaMotoristaDto motoristaAutomovelDto) {

        AtualizaMotoristaDto dto = motoristaService.atualizarMotorista(idMotorista, motoristaAutomovelDto);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{idMotorista}")
    public ResponseEntity<MotoristaAutomovelDto> atualizarMotoristaAutomovel(@PathVariable @NotNull Long idMotorista) {

        MotoristaAutomovelDto id = motoristaService.findById(idMotorista);

        return ResponseEntity.ok(id);
    }


    @DeleteMapping("/{idMotorista}")
    public ResponseEntity<String> deletaMotorista(@PathVariable @NotNull Long idMotorista) {

        motoristaService.deletarPeloId(idMotorista);

        return ResponseEntity.noContent().build();
    }


}



