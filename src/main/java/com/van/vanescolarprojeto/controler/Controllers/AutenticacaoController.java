package com.van.vanescolarprojeto.controler.Controllers;

import com.van.vanescolarprojeto.controler.Dtos.token.TokenDto;
import com.van.vanescolarprojeto.controler.Forms.Logins.LoginForm;
import com.van.vanescolarprojeto.controler.secutiry.Motorista.TokenServiceMotorista;
import com.van.vanescolarprojeto.controler.secutiry.Responsavel.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private TokenServiceMotorista tokenServiceMotorista;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/responsavel")
    public ResponseEntity<TokenDto> autenticarResponsavel(@RequestBody LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarTokenResponsavel(authentication);

            return ResponseEntity.ok(new TokenDto(token,"Bearer"));
        }catch (AuthenticationException e){
            e.printStackTrace();
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/motorista")
    public ResponseEntity<TokenDto> autenticarMotorista (@RequestBody LoginForm form) {
       UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            String token = tokenServiceMotorista.gerarTokenMotorista(authenticate);
            return ResponseEntity.ok(new TokenDto(token,"Bearer"));
        }catch (AuthenticationException e){
            e.printStackTrace();
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}

