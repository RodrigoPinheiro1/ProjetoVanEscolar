package com.van.vanescolarprojeto.service;

import com.van.vanescolarprojeto.dto.*;
import com.van.vanescolarprojeto.modelo.*;
import com.van.vanescolarprojeto.repository.MotoristaRepository;
import com.van.vanescolarprojeto.repository.ResponsavelRepository;
import com.van.vanescolarprojeto.utils.MapperMotoristaUtils;
import mocks.MockObjects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MotoristaServiceTest {

    @Mock
    private MotoristaRepository motoristaRepository;

    @Mock
    private GeradorArquivoService geradorArquivoService;
    @Mock
    private ResponsavelRepository responsavelRepository;

    @Mock
    private MapperMotoristaUtils mapperMotoristaUtils;

    @Mock
    private MapperResponsavelUtils mapperResponsavelUtils;

    @InjectMocks
    private MotoristaService motoristaService;

    @Test
    void testCadastrarMotorista() {
        MotoristaAutomovelDto dto = MockObjects.motoristaAutomovelDto();
        Motorista motorista = MockObjects.motorista();

        when(mapperMotoristaUtils.motoristaAutomovelDTOtoMotorista(dto)).thenReturn(motorista);
        when(mapperMotoristaUtils.motoristaToMotoristaAutomovelDTO(motorista)).thenReturn(dto);

        MotoristaAutomovelDto result = motoristaService.cadastrarMotorista(dto);

        verify(motoristaRepository).save(motorista);
        verify(geradorArquivoService).salvarMotoristaEmArquivo(motorista);
        assertEquals(dto, result);
    }

    @Test
    void testAcharMotorista() {
        Pageable pageable = PageRequest.of(0, 10);
        Motorista motorista = MockObjects.motorista();
        MotoristaDto dto = new MotoristaDto();

        when(motoristaRepository.findByEndereco_CidadeOrEndereco_Bairro("cidade", "bairro", pageable))
                .thenReturn(new PageImpl<>(List.of(motorista)));

        when(mapperMotoristaUtils.motoristaToMotoristaDTO(motorista)).thenReturn(dto);

        Page<MotoristaDto> result = motoristaService.acharMotorista("cidade", "bairro", pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(dto, result.getContent().get(0));
    }

    @Test
    void testVerPedidosCorridas() {
        Pageable pageable = PageRequest.of(0, 10);
        Responsavel responsavel = MockObjects.responsavel();
        ResponsavelDto dto = new ResponsavelDto();

        when(responsavelRepository.acharPorPedidoFeito(1L, pageable))
                .thenReturn(new PageImpl<>(List.of(responsavel)));

        when(mapperResponsavelUtils.responsavelToResponsavelDTO(responsavel)).thenReturn(dto);

        Page<ResponsavelDto> result = motoristaService.verPedidosCorridas(1L, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(dto, result.getContent().get(0));
    }

    @Test
    void testAtualizarMotorista() {
        AtualizaMotoristaDto dto = new AtualizaMotoristaDto();
        dto.setNome("João");
        dto.setCnh("123");
        dto.setTelefone("999");
        dto.setEndereco(MockObjects.endereco());

        Motorista motorista = MockObjects.motorista();
        motorista.setAutomovel(new Automovel()); // necessário para simular automóvel

        when(motoristaRepository.findById(1L)).thenReturn(Optional.of(new Motorista()));
        when(mapperMotoristaUtils.atualizaMotoristaDtoToMotorista(dto)).thenReturn(motorista);
        when(mapperMotoristaUtils.motoristaToAtualizaMotoristaDTO(motorista)).thenReturn(dto);

        AtualizaMotoristaDto result = motoristaService.atualizarMotorista(1L, dto);

        verify(motoristaRepository).save(motorista);
        verify(geradorArquivoService).salvarMudancaEmArquivo(any(), eq(motorista)); // caso queira simular diferença
        assertEquals(dto, result);

    }

    @Test
    void testFindById() {
        Motorista motorista = new Motorista();
        MotoristaAutomovelDto dto = new MotoristaAutomovelDto();

        when(motoristaRepository.findById(1L)).thenReturn(Optional.of(motorista));
        when(mapperMotoristaUtils.motoristaToMotoristaAutomovelDTO(motorista)).thenReturn(dto);

        MotoristaAutomovelDto result = motoristaService.findById(1L);

        assertEquals(dto, result);
    }

    @Test
    void testDeletarPeloId() {
        motoristaService.deletarPeloId(1L);
        verify(motoristaRepository).deleteById(1L);
    }

    @Test
    void testAceitarCorrida() {
        Long idMotorista = 1L;
        Long idResponsavel = 10L;

        PedidoCorridaMotoristaDto pedidoDto = new PedidoCorridaMotoristaDto();
        pedidoDto.setIdResponsavel(idResponsavel);

        Responsavel responsavel = MockObjects.responsavel();
        Motorista motorista = MockObjects.motorista();
        ResponsavelMotoristaDto dtoEsperado = new ResponsavelMotoristaDto();

        Mockito.when(responsavelRepository.findById(idResponsavel)).thenReturn(Optional.of(responsavel));
        Mockito.when(motoristaRepository.findById(idMotorista)).thenReturn(Optional.of(motorista));
        Mockito.when(mapperResponsavelUtils.responsavelToResponsavelMotoristaDTO(responsavel)).thenReturn(dtoEsperado);

        ResponsavelMotoristaDto resultado = motoristaService.aceitarCorrida(idMotorista, pedidoDto);

        assertEquals(StatusPedidoCorrida.Pedido_Aceito, responsavel.getStatusPedidoCorrida());
        assertEquals(StatusPedidoCorrida.Pedido_Aceito, motorista.getStatusPedidoCorrida());
        assertEquals(motorista, responsavel.getMotorista());
        assertEquals(dtoEsperado, resultado);

        verify(responsavelRepository).save(responsavel);
    }

    @Test
    void testNegarCorrida() {
        Long idMotorista = 1L;
        Long idResponsavel = 10L;

        PedidoCorridaMotoristaDto pedidoDto = new PedidoCorridaMotoristaDto();
        pedidoDto.setIdResponsavel(idResponsavel);

        Responsavel responsavel = MockObjects.responsavel();
        Motorista motorista = MockObjects.motorista();
        ResponsavelMotoristaDto dtoEsperado = new ResponsavelMotoristaDto();

        Mockito.when(responsavelRepository.findById(idResponsavel)).thenReturn(Optional.of(responsavel));
        Mockito.when(motoristaRepository.findById(idMotorista)).thenReturn(Optional.of(motorista));
        Mockito.when(mapperResponsavelUtils.responsavelToResponsavelMotoristaDTO(responsavel)).thenReturn(dtoEsperado);

        ResponsavelMotoristaDto resultado = motoristaService.negarCorrida(idMotorista, pedidoDto);

        assertEquals(StatusPedidoCorrida.Pedido_Negado, responsavel.getStatusPedidoCorrida());
        assertEquals(StatusPedidoCorrida.Pedido_Negado, motorista.getStatusPedidoCorrida());
        assertEquals(motorista, responsavel.getMotorista());
        assertEquals(dtoEsperado, resultado);

        verify(responsavelRepository).save(responsavel);
    }
}