package com.van.vanescolarprojeto.secutiry;

import com.van.vanescolarprojeto.Modelo.Usuario;
import com.van.vanescolarprojeto.Repository.UsuarioRepository;
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




    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;



    public AutenticacaoViaToken(UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;

        this.tokenService = tokenService;

    }


    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length()); //pega apartir do espaço
    }



    private void autenticar(String token) {
        Long id = tokenService.getId(token);
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario,
                null, usuario.get().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);
        boolean valido = tokenService.isTokenValido(token);
        if (valido) {
            autenticar(token);
        }
        filterChain.doFilter(request, response);
    }
}

