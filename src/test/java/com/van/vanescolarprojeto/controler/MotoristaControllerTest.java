package com.van.vanescolarprojeto.controler;

import com.van.vanescolarprojeto.dto.AtualizaMotoristaDto;
import com.van.vanescolarprojeto.dto.MotoristaAutomovelDto;
import com.van.vanescolarprojeto.dto.MotoristaDto;
import com.van.vanescolarprojeto.dto.ResponsavelDto;
import com.van.vanescolarprojeto.service.MotoristaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static mocks.MockObjects.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MotoristaControllerTest {


    @Mock
    private MotoristaService motoristaService;


    @InjectMocks
    private MotoristaController motoristaController;


    @Test
    void cadastrarMotorista() {

        when(motoristaService.cadastrarMotorista(any())).
                thenReturn(motoristaAutomovelDto());


        ResponseEntity<MotoristaAutomovelDto> result = motoristaController.cadastrarMotorista(motoristaAutomovelDto());


        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    void verPedidosCorridas() {

        when(motoristaService.verPedidosCorridas(any(), any())).
                thenReturn(responsavelDtoPage());


        Page<ResponsavelDto> responsavelDtos = motoristaController.verPedidosCorridas(1L, Pageable.unpaged());


        assertNotNull(responsavelDtos);

    }



    @Test
    void atualizarMotorista() {


        when(motoristaService.atualizarMotorista(any(), any())).
                thenReturn(new AtualizaMotoristaDto());


        ResponseEntity<AtualizaMotoristaDto> result = motoristaController.atualizarMotorista(1L, new AtualizaMotoristaDto());

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result);


    }


    @Test
    void pageMotorista() {


        when(motoristaService.acharMotorista(any(), any(),any())).
                thenReturn(motoristaDtoPage());


        Page<MotoristaDto> motoristaDtos = motoristaController.procurarMotorista
                ("cidade", "vbairro", Pageable.unpaged());


        assertNotNull(motoristaDtos);

    }


    @Test
    void deletaMotorista() {


        motoristaService.deletarPeloId(any());

        ResponseEntity<String> result = motoristaController.deletaMotorista(1L);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        assertNotNull(result);

    }
}