package com.van.vanescolarprojeto.controler.secutiry;

import com.van.vanescolarprojeto.Modelo.Responsavel;
import com.van.vanescolarprojeto.Repository.ResponsavelRepository;
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

    public AutenticacaoViaToken(ResponsavelRepository responsavelRepository, TokenService tokenService) {
        this.responsavelRepository = responsavelRepository;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean valido = tokenService.isTokenValido(token);
        if (valido){
            autenticarCliente(token);
        }
        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        Long idReponsavel = tokenService.getIdResponsavel(token);
        Optional<Responsavel> responsavel = responsavelRepository.findById(idReponsavel);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(responsavel, null, responsavel.get().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length()); //pega apartir do espaço
    }
}

