package com.van.vanescolarprojeto.Repository;

import com.van.vanescolarprojeto.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {



}
