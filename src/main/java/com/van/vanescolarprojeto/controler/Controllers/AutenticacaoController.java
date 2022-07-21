package com.van.vanescolarprojeto.controler.Controllers;

import com.van.vanescolarprojeto.controler.Dtos.token.TokenDto;
import com.van.vanescolarprojeto.controler.Forms.Logins.LoginForm;
import com.van.vanescolarprojeto.secutiry.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
@Profile(value = {"prod","test"})
public class AutenticacaoController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<TokenDto> autenticarResponsavel(@RequestBody LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            e.printStackTrace();
         return  ResponseEntity.badRequest().build();
        }
    }



}

