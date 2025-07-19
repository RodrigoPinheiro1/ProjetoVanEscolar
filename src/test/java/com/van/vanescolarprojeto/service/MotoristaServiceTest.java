package com.van.vanescolarprojeto.service;

import com.van.vanescolarprojeto.dto.AtualizaMotoristaDto;
import com.van.vanescolarprojeto.dto.MotoristaAutomovelDto;
import com.van.vanescolarprojeto.dto.MotoristaDto;
import com.van.vanescolarprojeto.dto.ResponsavelDto;
import com.van.vanescolarprojeto.modelo.Automovel;
import com.van.vanescolarprojeto.modelo.Endereco;
import com.van.vanescolarprojeto.modelo.Motorista;
import com.van.vanescolarprojeto.modelo.Responsavel;
import com.van.vanescolarprojeto.repository.MotoristaRepository;
import com.van.vanescolarprojeto.repository.ResponsavelRepository;
import com.van.vanescolarprojeto.utils.MapperMotoristaUtils;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MotoristaServiceTest {

    @Mock
    private MotoristaRepository motoristaRepository;

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
        MotoristaAutomovelDto dto = new MotoristaAutomovelDto();
        Motorista motorista = new Motorista();

        Mockito.when(mapperMotoristaUtils.motoristaAutomovelDTOtoMotorista(dto)).thenReturn(motorista);
        Mockito.when(mapperMotoristaUtils.motoristaToMotoristaAutomovelDTO(motorista)).thenReturn(dto);

        MotoristaAutomovelDto result = motoristaService.cadastrarMotorista(dto);

        Mockito.verify(motoristaRepository).save(motorista);
        Assertions.assertEquals(dto, result);
    }

    @Test
    void testAcharMotorista() {
        Pageable pageable = PageRequest.of(0, 10);
        Motorista motorista = new Motorista();
        MotoristaDto dto = new MotoristaDto();

        Mockito.when(motoristaRepository.findByEndereco_CidadeOrEndereco_Bairro("cidade", "bairro", pageable))
                .thenReturn(new PageImpl<>(List.of(motorista)));

        Mockito.when(mapperMotoristaUtils.motoristaToMotoristaDTO(motorista)).thenReturn(dto);

        Page<MotoristaDto> result = motoristaService.acharMotorista("cidade", "bairro", pageable);

        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(dto, result.getContent().get(0));
    }

    @Test
    void testVerPedidosCorridas() {
        Pageable pageable = PageRequest.of(0, 10);
        Responsavel responsavel = new Responsavel();
        ResponsavelDto dto = new ResponsavelDto();

        Mockito.when(responsavelRepository.acharPorPedidoFeito(1L, pageable))
                .thenReturn(new PageImpl<>(List.of(responsavel)));

        Mockito.when(mapperResponsavelUtils.responsavelToResponsavelDTO(responsavel)).thenReturn(dto);

        Page<ResponsavelDto> result = motoristaService.verPedidosCorridas(1L, pageable);

        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(dto, result.getContent().get(0));
    }

    @Test
    void testAtualizarMotorista() {
        AtualizaMotoristaDto dto = new AtualizaMotoristaDto();
        dto.setNome("João");
        dto.setCnh("123");
        dto.setTelefone("999");
        Endereco endereco = new Endereco();
        dto.setEndereco(endereco);
        Automovel automovel = new Automovel();
        Motorista motorista = new Motorista();
        motorista.setAutomovel(automovel);

        Mockito.when(motoristaRepository.findById(1L)).thenReturn(Optional.of(new Motorista()));
        Mockito.when(mapperMotoristaUtils.atualizaMotoristaDtoToMotorista(dto)).thenReturn(motorista);
        Mockito.when(mapperMotoristaUtils.motoristaToAtualizaMotoristaDTO(motorista)).thenReturn(dto);

        AtualizaMotoristaDto result = motoristaService.atualizarMotorista(1L, dto);

        Mockito.verify(motoristaRepository).save(motorista);
        Assertions.assertEquals(dto, result);
    }

    @Test
    void testFindById() {
        Motorista motorista = new Motorista();
        MotoristaAutomovelDto dto = new MotoristaAutomovelDto();

        Mockito.when(motoristaRepository.findById(1L)).thenReturn(Optional.of(motorista));
        Mockito.when(mapperMotoristaUtils.motoristaToMotoristaAutomovelDTO(motorista)).thenReturn(dto);

        MotoristaAutomovelDto result = motoristaService.findById(1L);

        Assertions.assertEquals(dto, result);
    }

    @Test
    void testDeletarPeloId() {
        motoristaService.deletarPeloId(1L);
        Mockito.verify(motoristaRepository).deleteById(1L);
    }
}