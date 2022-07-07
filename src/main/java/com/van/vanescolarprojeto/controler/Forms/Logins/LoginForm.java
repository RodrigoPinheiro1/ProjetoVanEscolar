package com.van.vanescolarprojeto.controler.Forms.Logins;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class LoginForm {

    private String email;
    private String senha;


    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email,senha);
    }
}
