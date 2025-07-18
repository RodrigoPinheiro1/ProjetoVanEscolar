package com.van.vanescolarprojeto.controler;


import com.van.vanescolarprojeto.dto.PedidoCorridaResponsavelDto;
import com.van.vanescolarprojeto.dto.ResponsavelDto;
import com.van.vanescolarprojeto.dto.ResponsavelMotoristaDto;
import com.van.vanescolarprojeto.service.ResponsavelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/responsaveis")
@RequiredArgsConstructor
public class ResponsavelController {

    private final ResponsavelService responsavelService;

    @PostMapping
    public ResponseEntity<ResponsavelDto> cadastrarResponsavel(@RequestBody @Valid ResponsavelDto dto) {


        ResponsavelDto responsavelDto = responsavelService.cadastrarResponsavel(dto);
           return new ResponseEntity<>(responsavelDto, HttpStatus.CREATED);

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