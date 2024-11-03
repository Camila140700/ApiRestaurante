package com.mx.apiRestaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mx.apiRestaurante.dao.MeserosDao;
import com.mx.apiRestaurante.model.Meseros;

@Service
public class MeserosImp {

	@Autowired
	MeserosDao meserosDao;

	@Transactional
	public List<Meseros> listar() {
		return meserosDao.findAll();
	}

	@Transactional
	public String guardar(Meseros mesero) {

		String respuesta = "";
		boolean bandera = false;

		for (Meseros m : meserosDao.findAll()) {
			// Verificar si el ID ya existe
			if (m.getId().equals(mesero.getId())) {
				bandera = true;
				respuesta = "idExiste";
				break;
			}
			// Verificar si el nombre completo ya existe
			else if (m.getNombre().equals(mesero.getNombre()) && m.getApp().equals(mesero.getApp())
					&& ((m.getApm() == null && mesero.getApm() == null)
							|| (m.getApm() != null && m.getApm().equals(mesero.getApm())))) {
				bandera = true;
				respuesta = "nombreCompletoExiste";
				break;
			}
		}

		// Si no se encuentra registrado ni en ID ni en nombre completo, se guarda
		if (!bandera) {
			meserosDao.save(mesero);
			respuesta = "guardado";
		}

		return respuesta;
	}

	@Transactional(readOnly = true)
	public Meseros buscar(Long id) {
		return meserosDao.findById(id).orElse(null);
	}

	@Transactional
	public boolean editar(Meseros meseros) {
		boolean bandera = false;
		for (Meseros m : meserosDao.findAll()) {
			if (m.getId().equals(meseros.getId())) {
				meserosDao.save(meseros);
				bandera = true;
				break;
			}
		}
		return bandera;

	}

	public boolean eliminar(Long id) {
		boolean bandera = false;
		for (Meseros m : meserosDao.findAll()) {
			if (m.getId().equals(id)) {
				meserosDao.deleteById(id);
				bandera = true;
				break;
			}
		}
		return bandera;
	}

}
