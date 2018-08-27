package com.petapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petapp.model.OrdemDeServico;

public interface OsRepository extends JpaRepository<OrdemDeServico, Long> {

}
