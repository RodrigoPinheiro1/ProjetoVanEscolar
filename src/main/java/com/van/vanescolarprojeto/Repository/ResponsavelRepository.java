package com.van.vanescolarprojeto.Repository;

import com.van.vanescolarprojeto.Modelo.Responsavel;
import com.van.vanescolarprojeto.controler.Dtos.Responsavel.ResponsavelDto;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel,Long> {


    Page<Responsavel> findByNome(String nome, Pageable pageable);

    Optional <Responsavel> findByEmail(String username);
}
