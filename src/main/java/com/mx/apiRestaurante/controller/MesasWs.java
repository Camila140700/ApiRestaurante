package com.mx.apiRestaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.apiRestaurante.model.Mesas;

import com.mx.apiRestaurante.service.MesasImp;

@RestController
@RequestMapping(path = "MesasWebService")
@CrossOrigin

public class MesasWs {

	@Autowired
	MesasImp mesasImp;

	// URL: http://localhost:9002/MesasWebService/listar
	@PostMapping(path = "listar")
	public List<Mesas> listar() {
		return mesasImp.listar();
	}

	// URL: http://localhost:9002/MesasWebService/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Mesas mesa) {
		String response = mesasImp.guardar(mesa);

		if (response.equals("idMeseroNoExiste")) {
			return new ResponseEntity<>("No se guardó, ese idMesero no existe", HttpStatus.OK);
		} else if (response.equals("idMesaExiste")) {
			return new ResponseEntity<>("No se guardó, ese idMesa ya existe", HttpStatus.OK);
		} else if (response.equals("numMesaExiste")) {
			return new ResponseEntity<>("No se guardó, ese numMesa ya existe", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(mesa, HttpStatus.CREATED);
		}
	}

	// URL: http://localhost:9002/MesasWebService/guardar
	@PostMapping(path = "buscar")
	public ResponseEntity<?> buscar(@RequestBody Mesas mesas) {
		Mesas mesaEnc = mesasImp.buscar(mesas.getId());

		if (mesaEnc == null) {
			return new ResponseEntity<>("Esa mesa no existe", HttpStatus.OK);
		}

		return new ResponseEntity<>(mesaEnc, HttpStatus.OK);
	}

	// URL: http://localhost:9002/MesasWebService/listar
	@PostMapping("editar")
	public ResponseEntity<?> editarMesa(@RequestBody Mesas mesa) {
		String response = mesasImp.editar(mesa);

		if (response.equals("idMesaNoExiste")) {
			return new ResponseEntity<>("No se actualizó, ese id de mesa no existe", HttpStatus.OK);
		} else if (response.equals("idMeseroNoExiste")) {
			return new ResponseEntity<>("No se actualizó, ese id de mesero no existe", HttpStatus.OK);
		} else if (response.equals("actualizado")) {
			return new ResponseEntity<>(mesa, HttpStatus.OK); 
		} else {
			return new ResponseEntity<>("Error inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// URL: http://localhost:9002/MesasWebService/eliminar
	@PostMapping(path = "eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Mesas mesa) {
		boolean response = mesasImp.eliminar(mesa.getId());

		if (!response) {
			return new ResponseEntity<>("Ese registro no existe", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("Se eliminó con éxito", HttpStatus.OK);
		}
	}

}
