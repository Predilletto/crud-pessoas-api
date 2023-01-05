package com.predilletto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.predilletto.api.domain.model.Pessoa;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
