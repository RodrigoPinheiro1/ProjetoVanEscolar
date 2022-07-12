package com.van.vanescolarprojeto.controler.secutiry;

import com.van.vanescolarprojeto.Repository.MotoristaRepository;
import com.van.vanescolarprojeto.Repository.ParceiroMotoristaRepository;
import com.van.vanescolarprojeto.Repository.ResponsavelRepository;
import com.van.vanescolarprojeto.controler.secutiry.Motorista.TokenServiceMotorista;
import com.van.vanescolarprojeto.controler.secutiry.ParceiroMotorista.TokenServiceParceiroMotorista;
import com.van.vanescolarprojeto.controler.secutiry.Responsavel.TokenServiceResponsavel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecutiryConfigurations {

    @Autowired
    private TokenServiceResponsavel tokenServiceResponsavel;
    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private TokenServiceMotorista tokenServiceMotorista;

    @Autowired
    private MotoristaRepository motoristaRepository;


    @Autowired
    private  ParceiroMotoristaRepository parceiroMotoristaRepository;
    @Autowired
    private  TokenServiceParceiroMotorista tokenServiceParceiroMotorista;



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
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll() //MUDAR pois actuator devolve informações sensiveis da aplicação,
                .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                .anyRequest().authenticated() //qualquer outra requeste, precisa estar autenticado,
                .and().csrf().disable() //csrf protecao contra hackers, nao necessario pelos tokens,
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //politica da api, statelles,
                .and().addFilterBefore(new AutenticacaoViaToken(responsavelRepository, tokenServiceResponsavel,
                                tokenServiceMotorista,
                                motoristaRepository,
                                parceiroMotoristaRepository, tokenServiceParceiroMotorista),
                        UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
    @Bean //configurações de recursos estaticos (imagens, etc.)
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }

    /*public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
*/
}
