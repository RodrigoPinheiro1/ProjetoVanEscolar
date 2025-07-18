package com.van.vanescolarprojeto.Repository;

import com.van.vanescolarprojeto.modelo.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel,Long> {




}
