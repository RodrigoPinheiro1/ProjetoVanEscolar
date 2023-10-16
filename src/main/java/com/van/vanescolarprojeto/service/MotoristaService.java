package com.van.vanescolarprojeto.service;

import com.van.vanescolarprojeto.Dto.*;
import com.van.vanescolarprojeto.Modelo.Automovel;
import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Modelo.Responsavel;
import com.van.vanescolarprojeto.Modelo.StatusPedidoCorrida;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.Repository.ResponsavelRepository;
import com.van.vanescolarprojeto.exceptions.UsuarioNaoEncontrado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private ModelMapper modelMapper;

    public MotoristaAutomovelDto cadastrarMotorista(MotoristaAutomovelDto motoristaAutomovelDto) {

        Motorista motorista = modelMapper.map(motoristaAutomovelDto, Motorista.class);
        Automovel automovel = modelMapper.map(motoristaAutomovelDto.getAutomovel(), Automovel.class);

        motorista.setAutomovel(automovel);
        motoristaRepository.save(motorista);


        return modelMapper.map(motorista, MotoristaAutomovelDto.class);
    }

    public Page<MotoristaDto> acharMotorista(String cidade, String bairro, Pageable pageable) {

        return motoristaRepository.findByEndereco_CidadeOrEndereco_Bairro(cidade, bairro, pageable)
                .map(motorista -> modelMapper.map(motorista, MotoristaDto.class));
    }


    public Page<ResponsavelDto> verPedidosCorridas(Long idMotorista, Pageable pageable) {

        return responsavelRepository.acharPorPedidoFeito(idMotorista, pageable)
                .map(responsavel -> modelMapper.map(responsavel, ResponsavelDto.class));
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

        return modelMapper.map(responsavel, ResponsavelMotoristaDto.class);
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


        return modelMapper.map(responsavel, ResponsavelMotoristaDto.class);
    }

    public AtualizaMotoristaDto atualizarMotorista(Long idMotorista, AtualizaMotoristaDto dto) {


        motoristaRepository.findById(idMotorista).orElseThrow(UsuarioNaoEncontrado::new);
        Motorista motorista = modelMapper.map(dto, Motorista.class);

        Automovel automovel = modelMapper.map(dto.getAutomovel(), Automovel.class);

        motorista.setId(idMotorista);
        motorista.setNome(dto.getNome());
        motorista.setCnh(dto.getCnh());
        motorista.setTelefone(dto.getTelefone());
        motorista.setEndereco(dto.getEndereco());

        motorista.setAutomovel(automovel);

        motoristaRepository.save(motorista);

        return modelMapper.map(motorista, AtualizaMotoristaDto.class);
    }


    public MotoristaAutomovelDto findById(Long idMotorista) {

        Motorista motorista = motoristaRepository.findById(idMotorista).orElseThrow(UsuarioNaoEncontrado::new);
        return modelMapper.map(motorista, MotoristaAutomovelDto.class);

    }
}
