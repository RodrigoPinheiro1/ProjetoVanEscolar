package com.van.vanescolarprojeto.controler.secutiry;

import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Modelo.Responsavel;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.Repository.ResponsavelRepository;
import com.van.vanescolarprojeto.controler.secutiry.Motorista.TokenServiceMotorista;
import com.van.vanescolarprojeto.controler.secutiry.Responsavel.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class AutenticacaoViaToken extends OncePerRequestFilter {

    private ResponsavelRepository responsavelRepository;
    private TokenService tokenService;
    private TokenServiceMotorista tokenServiceMotorista;

    private MotoristaRepository motoristaRepository; //não é possivel colocar dependencias


    public AutenticacaoViaToken(ResponsavelRepository responsavelRepository,
                                TokenService tokenService, TokenServiceMotorista tokenServiceMotorista, MotoristaRepository motoristaRepository) {
        this.responsavelRepository = responsavelRepository;
        this.tokenService = tokenService;
        this.tokenServiceMotorista = tokenServiceMotorista;
        this.motoristaRepository = motoristaRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean valido = tokenService.isTokenValido(token);
        boolean valido2 = tokenServiceMotorista.isTokenValido(token);
        if (valido){
            autenticarResponsavel(token);
        } else if (valido2){
            autenticarMotorista(token);
        }
        filterChain.doFilter(request, response);
    }

    private void autenticarResponsavel(String token) {
        Long idReponsavel = tokenService.getIdResponsavel(token);
        Optional<Responsavel> responsavel = responsavelRepository.findById(idReponsavel);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(responsavel,
                null, responsavel.get().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length()); //pega apartir do espaço
    }
    private void autenticarMotorista(String token) {
        Long idMotorista = tokenServiceMotorista.getIdMotorista(token);
        Optional<Motorista> motorista = motoristaRepository.findById(idMotorista);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(motorista,
                null,motorista.get().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}

