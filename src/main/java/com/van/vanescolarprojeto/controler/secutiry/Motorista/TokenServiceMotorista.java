package com.van.vanescolarprojeto.controler.secutiry.Motorista;

import com.van.vanescolarprojeto.Modelo.Motorista;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceMotorista {


    @Value("864000")
    private String expiration;
    @Value("${forum.jwt.secret}")
    private String secret;

    public String gerarTokenMotorista(Authentication authentication){
        Motorista motorista = (Motorista) authentication.getPrincipal(); //pega o usuario
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("VanEscolarProjeto") //qual aplicação está fazendo o token
                .setSubject(motorista.getId().toString())  //dono do token;
                .setIssuedAt(hoje) //data expiração
                .setExpiration(dataExpiracao) //tempo que o token é valido
                .signWith(SignatureAlgorithm.HS256,secret) // HS256,gera o algoritmo de cripografia   cripocrafia do token obs "buscar um programa que faça uma senha aleatoria grande de numeros
                .compact();

    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Long getIdMotorista(String token) {

        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject()); //pega o id getSubject
    }
}
