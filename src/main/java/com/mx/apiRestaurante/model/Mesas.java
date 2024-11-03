package com.mx.apiRestaurante.model;





import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "MESAS_RES")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mesas {

	
	@Id
	private Long id;
	private Long numMesa;
	private Long numSillas;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name= "ID_MESERO" )
	Meseros mesero;
}
