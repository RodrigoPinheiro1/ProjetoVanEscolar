package com.van.vanescolarprojeto.controler.secutiry;

import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Modelo.ParceiroMotorista;
import com.van.vanescolarprojeto.Modelo.Responsavel;
import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.Repository.ParceiroMotoristaRepository;
import com.van.vanescolarprojeto.Repository.ResponsavelRepository;
import com.van.vanescolarprojeto.controler.secutiry.Motorista.TokenServiceMotorista;
import com.van.vanescolarprojeto.controler.secutiry.ParceiroMotorista.TokenServiceParceiroMotorista;
import com.van.vanescolarprojeto.controler.secutiry.Responsavel.TokenServiceResponsavel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AutenticacaoViaToken extends OncePerRequestFilter {

    private final ResponsavelRepository responsavelRepository;
    private final TokenServiceResponsavel tokenServiceResponsavel;
    private final TokenServiceMotorista tokenServiceMotorista;
    private final MotoristaRepository motoristaRepository; //não é possivel colocar dependencias

    private final ParceiroMotoristaRepository parceiroMotoristaRepository;
    private final TokenServiceParceiroMotorista tokenServiceParceiroMotorista;


    public AutenticacaoViaToken(ResponsavelRepository responsavelRepository,
                                TokenServiceResponsavel tokenServiceResponsavel, TokenServiceMotorista tokenServiceMotorista,
                                MotoristaRepository motoristaRepository, ParceiroMotoristaRepository parceiroMotoristaRepository,
                                TokenServiceParceiroMotorista tokenServiceParceiroMotorista) {
        this.responsavelRepository = responsavelRepository;
        this.tokenServiceResponsavel = tokenServiceResponsavel;
        this.tokenServiceMotorista = tokenServiceMotorista;
        this.motoristaRepository = motoristaRepository;
        this.parceiroMotoristaRepository = parceiroMotoristaRepository;
        this.tokenServiceParceiroMotorista = tokenServiceParceiroMotorista;
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
                null, motorista.get().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private void autenticarParceiro(String token) {
        Long idParceiro = tokenServiceParceiroMotorista.getIdParceiro(token);
        Optional<ParceiroMotorista> parceiroMotorista = parceiroMotoristaRepository.findById(idParceiro);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(parceiroMotorista,
                null, parceiroMotorista.get().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    private void autenticarResponsavel(String token) {
        Long idReponsavel = tokenServiceResponsavel.getIdResponsavel(token);
        Optional<Responsavel> responsavel = responsavelRepository.findById(idReponsavel);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(responsavel,
                null, responsavel.get().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);
        boolean valido = tokenServiceResponsavel.isTokenValido(token);
        boolean valido2 = tokenServiceMotorista.isTokenValido(token);
        boolean valido3 = tokenServiceParceiroMotorista.isTokenValido(token);

        if (valido) {
            autenticarResponsavel(token);
        } else if (valido2) {
            autenticarMotorista(token);
        } else if (valido3) {
            autenticarParceiro(token);
        }
        filterChain.doFilter(request, response);
    }
}

