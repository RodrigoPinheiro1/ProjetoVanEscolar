package com.van.vanescolarprojeto.Repository;

import com.van.vanescolarprojeto.Modelo.Responsavel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel,Long> {



}
