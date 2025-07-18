package com.van.vanescolarprojeto.repository;

import com.van.vanescolarprojeto.modelo.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno , Long> {




}
