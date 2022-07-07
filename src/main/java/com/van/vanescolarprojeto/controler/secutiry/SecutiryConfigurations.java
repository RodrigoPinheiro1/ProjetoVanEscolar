package com.van.vanescolarprojeto.controler.secutiry;

import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.Repository.ResponsavelRepository;
import com.van.vanescolarprojeto.controler.secutiry.Motorista.TokenServiceMotorista;
import com.van.vanescolarprojeto.controler.secutiry.Responsavel.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecutiryConfigurations {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private TokenServiceMotorista tokenServiceMotorista;

    @Autowired
    private MotoristaRepository motoristaRepository;


    @Bean //geração do bCrypt
    protected PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean //configuração autenticação
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean //controle acesso, de perfil
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests() //autorização de metodos
                .antMatchers(HttpMethod.POST, "/responsavel").permitAll()
                .antMatchers(HttpMethod.POST, "/parceiroMotorista").permitAll()
                .antMatchers(HttpMethod.POST, "/motorista").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .anyRequest().authenticated() //qualquer outra requeste, precisa estar autenticado,
                .and().csrf().disable() //csrf protecao contra hackers, nao necessario pelos tokens,
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //politica da api, statelles,
                .and().addFilterBefore(new AutenticacaoViaToken(responsavelRepository, tokenService,
                                tokenServiceMotorista,
                                motoristaRepository),
                        UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    /*public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
*/
}
