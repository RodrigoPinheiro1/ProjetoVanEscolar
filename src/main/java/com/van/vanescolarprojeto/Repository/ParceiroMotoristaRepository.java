package com.van.vanescolarprojeto.Repository;

import com.van.vanescolarprojeto.Modelo.ParceiroMotorista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParceiroMotoristaRepository  extends JpaRepository <ParceiroMotorista, Long>  {


    Page<ParceiroMotorista> findByNome(String nome, Pageable pageable);




   @Query(value = "DELETE FROM vanescolar.motorista_parceiro_motorista WHERE motorista_id=? AND parceiro_motorista_id=?", nativeQuery = true)
   ParceiroMotorista deletarVinculo(Long idMotorista, Long idParceiro);

   Optional<ParceiroMotorista> findByEmail(String username);
}
