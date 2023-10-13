package com.van.vanescolarprojeto.Repository;

import com.van.vanescolarprojeto.Modelo.Motorista;
import com.van.vanescolarprojeto.Modelo.Responsavel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista,Long> {



    Page<Motorista> findByEndereco_CidadeOrEndereco_Bairro(String cidade, String bairro, Pageable pageable);



}
