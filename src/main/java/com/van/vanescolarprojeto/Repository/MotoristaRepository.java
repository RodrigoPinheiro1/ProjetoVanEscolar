package com.van.vanescolarprojeto.Repository;

import com.van.vanescolarprojeto.Modelo.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista,Long> {



}
