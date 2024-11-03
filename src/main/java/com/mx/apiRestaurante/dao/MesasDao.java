package com.mx.apiRestaurante.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.apiRestaurante.model.Mesas;

public interface MesasDao extends JpaRepository<Mesas, Long> {

}
