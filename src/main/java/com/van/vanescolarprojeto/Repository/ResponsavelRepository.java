package com.van.vanescolarprojeto.Repository;

import com.van.vanescolarprojeto.Modelo.Responsavel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel,Long> {




    @Query("select r from Responsavel r where r.motorista.id =:idMotorista and r.statusPedidoCorrida = 'Feito_Pedido' ")
    Page<Responsavel> acharPorPedidoFeito(Long idMotorista, Pageable pageable);


    @Modifying
    @Transactional
    @Query("DELETE FROM Responsavel r where r.id =:id and r.motorista.id =:id2")
    void teste(Long id, Long id2);

}
