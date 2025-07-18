package com.van.vanescolarprojeto.service;

import com.van.vanescolarprojeto.dto.*;
import com.van.vanescolarprojeto.modelo.Automovel;
import com.van.vanescolarprojeto.modelo.Motorista;
import com.van.vanescolarprojeto.modelo.Responsavel;
import com.van.vanescolarprojeto.modelo.StatusPedidoCorrida;
import com.van.vanescolarprojeto.repository.MotoristaRepository;
import com.van.vanescolarprojeto.repository.ResponsavelRepository;
import com.van.vanescolarprojeto.exceptions.UsuarioNaoEncontrado;
import com.van.vanescolarprojeto.utils.MapperMotoristaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MotoristaService {


    private final MotoristaRepository motoristaRepository;

    private final ResponsavelRepository responsavelRepository;


    private final MapperMotoristaUtils mapperMotoristaUtils;
    private final MapperResponsavelUtils mapperResponsavelUtils;

    public MotoristaAutomovelDto cadastrarMotorista(MotoristaAutomovelDto motoristaAutomovelDto) {


        Motorista motorista = mapperMotoristaUtils.motoristaAutomovelDTOtoMotorista(motoristaAutomovelDto);

        motoristaRepository.save(motorista);

        return mapperMotoristaUtils.motoristaToMotoristaAutomovelDTO(motorista);
    }

    public Page<MotoristaDto> acharMotorista(String cidade, String bairro, Pageable pageable) {


        return motoristaRepository.findByEndereco_CidadeOrEndereco_Bairro(cidade, bairro, pageable)
                .map(mapperMotoristaUtils::motoristaToMotoristaDTO);
    }

    public Page<ResponsavelDto> verPedidosCorridas(Long idMotorista, Pageable pageable) {


        return responsavelRepository.acharPorPedidoFeito(idMotorista, pageable)
                .map(mapperResponsavelUtils::responsavelToResponsavelDTO);
    }


    public ResponsavelMotoristaDto aceitarCorrida(Long idMotorista, PedidoCorridaMotoristaDto pedidoCorridaMotoristaDto) {

        Responsavel responsavel = responsavelRepository.findById(pedidoCorridaMotoristaDto.getIdResponsavel())
                .orElseThrow(UsuarioNaoEncontrado::new);

        Motorista motorista = motoristaRepository.findById(idMotorista)
                .orElseThrow(UsuarioNaoEncontrado::new);

        responsavel.setStatusPedidoCorrida(StatusPedidoCorrida.Pedido_Aceito);
        motorista.setStatusPedidoCorrida(StatusPedidoCorrida.Pedido_Aceito);
        responsavel.setMotorista(motorista);

        responsavelRepository.save(responsavel);

        return mapperResponsavelUtils.responsavelToResponsavelMotoristaDTO(responsavel);
    }

    @Transactional
    public ResponsavelMotoristaDto negarCorrida(Long idMotorista, PedidoCorridaMotoristaDto pedidoCorridaMotoristaDto) {

        Responsavel responsavel = responsavelRepository.findById(pedidoCorridaMotoristaDto.getIdResponsavel())
                .orElseThrow(UsuarioNaoEncontrado::new);

        Motorista motorista = motoristaRepository.findById(idMotorista)
                .orElseThrow(UsuarioNaoEncontrado::new);

        responsavel.setStatusPedidoCorrida(StatusPedidoCorrida.Pedido_Negado);
        motorista.setStatusPedidoCorrida(StatusPedidoCorrida.Pedido_Negado);
        responsavel.setMotorista(motorista);

        responsavelRepository.save(responsavel);

        return mapperResponsavelUtils.responsavelToResponsavelMotoristaDTO(responsavel);
    }

    public AtualizaMotoristaDto atualizarMotorista(Long idMotorista, AtualizaMotoristaDto motoristaDto) {


        motoristaRepository.findById(idMotorista).orElseThrow(UsuarioNaoEncontrado::new);

        Motorista motorista = mapperMotoristaUtils.atualizaMotoristaDtoToMotorista(motoristaDto);

        motorista.setId(idMotorista);
        motorista.setNome(motoristaDto.getNome());
        motorista.setCnh(motoristaDto.getCnh());
        motorista.setTelefone(motoristaDto.getTelefone());
        motorista.setEndereco(motoristaDto.getEndereco());
        motorista.setAutomovel(motorista.getAutomovel());

        motoristaRepository.save(motorista);

        return mapperMotoristaUtils.motoristaToAtualizaMotoristaDTO(motorista);

    }


    public MotoristaAutomovelDto findById(Long idMotorista) {

        Motorista motorista = motoristaRepository.findById(idMotorista).orElseThrow(UsuarioNaoEncontrado::new);
        return mapperMotoristaUtils.motoristaToMotoristaAutomovelDTO(motorista);
    }

    public void deletarPeloId(Long idMotorista) {
        motoristaRepository.deleteById(idMotorista);
    }
}
