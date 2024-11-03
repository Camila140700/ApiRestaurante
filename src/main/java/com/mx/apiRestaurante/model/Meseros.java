package com.mx.apiRestaurante.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="MESERO_RES")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Meseros {
	@Id
	private Long id;
	private String nombre;
	private String app;
	private String apm;
	private Float sueldo;
	

	@OneToMany(mappedBy = "mesero", cascade=CascadeType.ALL)
	@JsonIgnore
	List<Mesas> lista = new ArrayList<Mesas>();

}
