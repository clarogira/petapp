package com.petapp.repository;

import com.petapp.model.TipoDePagamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposDePagamento extends JpaRepository<TipoDePagamento, Long>{

}
