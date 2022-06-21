package com.van.vanescolarprojeto.Repository;

import com.van.vanescolarprojeto.Modelo.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno , Long> {



}
