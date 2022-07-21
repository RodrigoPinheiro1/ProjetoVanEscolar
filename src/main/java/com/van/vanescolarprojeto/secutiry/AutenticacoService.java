package com.van.vanescolarprojeto.secutiry;

import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Modelo.ParceiroMotorista;
import com.van.vanescolarprojeto.Modelo.Responsavel;
import com.van.vanescolarprojeto.Modelo.Usuario;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.Repository.ParceiroMotoristaRepository;
import com.van.vanescolarprojeto.Repository.ResponsavelRepository;
import com.van.vanescolarprojeto.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AutenticacoService implements UserDetailsService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {



     Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

    if (usuario.isPresent()){
        return usuario.get();
    }

            throw new UsernameNotFoundException("dados invalidos");

    }

}
