package com.mx.apiRestaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mx.apiRestaurante.dao.MesasDao;
import com.mx.apiRestaurante.dao.MeserosDao;
import com.mx.apiRestaurante.model.Mesas;
import com.mx.apiRestaurante.model.Meseros;

@Service
public class MesasImp {

	@Autowired
	MesasDao mesasDao;
	@Autowired
	MeserosDao meserosDao;

	@Transactional
	public List<Mesas> listar() {
		return mesasDao.findAll();
	}

	public String guardar(Mesas mesa) {
		String respuesta = "";
		boolean meseroExiste = false;
		boolean mesaExiste = false;

		// Validar que el mesero asignado a la mesa existe
		for (Meseros mesero : meserosDao.findAll()) {
			if (mesero.getId().equals(mesa.getMesero().getId())) {
				meseroExiste = true;

				// Validar que no exista otra mesa con el mismo ID o numMesa
				for (Mesas me : mesasDao.findAll()) {
					if (me.getId().equals(mesa.getId())) {
						respuesta = "idMesaExiste";
						mesaExiste = true;
						break; 
					} else if (me.getNumMesa().equals(mesa.getNumMesa())) {
						respuesta = "numMesaExiste";
						mesaExiste = true;
						break; 
					}
				}
				break; 
			}
		}

		// Si el mesero no existe, retorna el mensaje correspondiente
		if (!meseroExiste) {
			respuesta = "idMeseroNoExiste";
		} else if (!mesaExiste) {
			mesasDao.save(mesa); // Guardamos la mesa solo si todas las validaciones son verdaderas
			respuesta = "guardado";
		}

		return respuesta;
	}

	@Transactional(readOnly = true)
	public Mesas buscar(Long id) {
		for (Mesas mesa : mesasDao.findAll()) {
			if (mesa.getId().equals(id)) {
				return mesa;
			}
		}
		return null;
	}

	@Transactional
	public String editar(Mesas mesa) {
		List<Mesas> listaMesas = mesasDao.findAll();
		List<Meseros> listaMeseros = meserosDao.findAll();

		boolean mesaExiste = false;
		boolean meseroExiste = false;

		// Verificar si la mesa existe
		for (Mesas m : listaMesas) {
			if (m.getId().equals(mesa.getId())) {
				mesaExiste = true;

				// Verificar si el mesero existe
				for (Meseros mesero : listaMeseros) {
					if (mesero.getId().equals(mesa.getMesero().getId())) {
						meseroExiste = true;
						// Si ambas condiciones son verdaderas, actualizamos la mesa
						m.setNumMesa(mesa.getNumMesa());
						m.setNumSillas(mesa.getNumSillas());
						m.setMesero(mesero);
						mesasDao.save(m); 
						return "actualizado"; 
					}
				}
				break; 
			}
		}

		if (!mesaExiste) {
			return "idMesaNoExiste"; // La mesa no existe
		}
		if (!meseroExiste) {
			return "idMeseroNoExiste"; // El mesero no existe
		}

		return "errorInesperado"; 
	}

	@Transactional
	public boolean eliminar(Long id) {
		boolean bandera = false; // Bandera para verificar la eliminación

		// Iterar sobre todas las mesas para buscar el ID
		for (Mesas mesa : mesasDao.findAll()) {
			if (mesa.getId().equals(id)) {
				mesasDao.deleteById(id); 
				bandera = true; 
				break; 
			}
		}

		return bandera; 
	}

}
