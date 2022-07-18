package com.van.vanescolarprojeto.controler.secutiry;

/*
@EnableWebSecurity
@Configuration

public class DevSecutiryConfigurations {


    @Bean //controle acesso, de perfil
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests() //autorização de metodos
                .antMatchers("/**").permitAll();
        return httpSecurity.build();
    }

    @Bean //configurações de recursos estaticos (imagens, etc.)
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }

    /*public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

}*/
