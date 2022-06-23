package com.van.vanescolarprojeto.Repository;

import com.van.vanescolarprojeto.Modelo.ParceiroMotorista;
import com.van.vanescolarprojeto.controler.Dtos.ParceiroMotorista.ParceiroMotoristaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParceiroMotoristaRepository  extends JpaRepository <ParceiroMotorista, Long>  {


    Page<ParceiroMotorista> findByNome(String nome, Pageable pageable);
}
