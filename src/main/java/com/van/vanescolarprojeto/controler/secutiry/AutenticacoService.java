package com.van.vanescolarprojeto.controler.secutiry;

import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Modelo.ParceiroMotorista;
import com.van.vanescolarprojeto.Modelo.Responsavel;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.Repository.ParceiroMotoristaRepository;
import com.van.vanescolarprojeto.Repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacoService implements UserDetailsService {


    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private ParceiroMotoristaRepository parceiroMotoristaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Responsavel> responsavel = responsavelRepository.findByEmail(username);
        Optional<ParceiroMotorista> parceiroMotorista = parceiroMotoristaRepository.findByEmail(username);
        Optional<Motorista> motorista = motoristaRepository.findByEmail(username);

        if (responsavel.isPresent()) {
            return responsavel.get();
        } if (motorista.isPresent()) {
            return motorista.get();
        } if (parceiroMotorista.isPresent()) {
           return parceiroMotorista.get();
        }
        throw new UsernameNotFoundException("dados invalidos");
    }
}
