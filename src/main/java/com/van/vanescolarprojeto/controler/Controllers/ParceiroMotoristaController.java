package com.van.vanescolarprojeto.controler.Controllers;


import com.van.vanescolarprojeto.Modelo.ParceiroMotorista;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.Repository.ParceiroMotoristaRepository;
import com.van.vanescolarprojeto.controler.Dtos.ParceiroMotorista.DetalhesParceiroDto;
import com.van.vanescolarprojeto.controler.Dtos.ParceiroMotorista.ParceiroMotoristaDto;
import com.van.vanescolarprojeto.controler.Forms.ParceiroMotorista.AtualizarParceiroForm;
import com.van.vanescolarprojeto.controler.Forms.ParceiroMotorista.ParceiroMotoristaForm;
import com.van.vanescolarprojeto.controler.Forms.ParceiroMotorista.RemoverVinculoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/parceiroMotorista")
public class ParceiroMotoristaController {

    @Autowired
    private ParceiroMotoristaRepository parceiroMotoristaRepository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesParceiroDto> cadastrarParceiro(@PathVariable Long id,
                                                                 @RequestBody @Valid ParceiroMotoristaForm parceiroMotoristaForm,
                                                                 UriComponentsBuilder uriComponentsBuilder) {

        ParceiroMotorista parceiroMotorista = parceiroMotoristaForm.cadastroComMotorista(id, motoristaRepository);
        parceiroMotoristaRepository.save(parceiroMotorista);

        URI uri = uriComponentsBuilder.path("/parceiroMotorista/{id}").buildAndExpand(parceiroMotorista.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesParceiroDto(parceiroMotorista));
    }

    @GetMapping
    @Transactional
    public Page<ParceiroMotoristaDto> paginacaoParceiro(@RequestParam(required = false) String nome,
                                                        @PageableDefault(sort = "id",
                                                                direction = Sort.Direction.ASC,
                                                                page = 0, size = 10) Pageable pageable) {

        if (nome == null) {
            Page<ParceiroMotorista> parceiroMotorista = parceiroMotoristaRepository.findAll(pageable);
            return ParceiroMotoristaDto.converter(parceiroMotorista);
        }
        Page<ParceiroMotorista> parceiroMotorista = parceiroMotoristaRepository.findByNome(nome, pageable);
        return ParceiroMotoristaDto.converter(parceiroMotorista);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ParceiroMotoristaDto> cadastroSimples(@RequestBody @Valid ParceiroMotoristaForm parceiroMotoristaForm,
                                                                UriComponentsBuilder uriComponentsBuilder) {

        ParceiroMotorista parceiroMotorista = parceiroMotoristaForm.cadastrar();
        parceiroMotoristaRepository.save(parceiroMotorista);


        URI uri = uriComponentsBuilder.path("/parceiroMotorista/{id}").buildAndExpand(parceiroMotorista.getId()).toUri();
        return ResponseEntity.created(uri).body(new ParceiroMotoristaDto(parceiroMotorista));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ParceiroMotoristaDto> atualizarVinculoMotorista(@PathVariable Long id,
                                                                          @RequestBody AtualizarParceiroForm
                                                                                  atualizarParceiroForm) {

        Optional<ParceiroMotorista> parceiroMotorista = parceiroMotoristaRepository.findById(id);

        if (parceiroMotorista.isPresent()) {

            atualizarParceiroForm.atualizarVinculo(id, parceiroMotoristaRepository,motoristaRepository);
            return ResponseEntity.ok(new ParceiroMotoristaDto(parceiroMotorista.get()));
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{idParceiro}")
    @Transactional
    public ResponseEntity<?> removerVinculoMotorista (Long idMotorista, @PathVariable Long idParceiro,
                                                      @RequestBody RemoverVinculoForm removerVinculoForm
                                                      ){

        Optional<ParceiroMotorista> parceiroMotorista = parceiroMotoristaRepository.findById(idParceiro);

        if (parceiroMotorista.isPresent()) {

            removerVinculoForm.getIdMotorista(parceiroMotoristaRepository, idParceiro);


            return ResponseEntity.ok().build();
        }


        return ResponseEntity.notFound().build();
    }


}
