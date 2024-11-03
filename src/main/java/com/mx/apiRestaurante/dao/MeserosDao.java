package com.mx.apiRestaurante.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.apiRestaurante.model.Meseros;

public interface MeserosDao extends JpaRepository<Meseros, Long> {

}
