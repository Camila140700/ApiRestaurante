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

import com.mx.apiRestaurante.model.Meseros;
import com.mx.apiRestaurante.service.MeserosImp;




@RestController
@RequestMapping(path = "MeserosWebService")
@CrossOrigin
public class MeserosWs {

	@Autowired
	MeserosImp meserosImp;
	
	// URL: http://localhost:9002/MeserosWebService/listar
	@PostMapping(path = "listar")
	public List<Meseros> listar() {
		return meserosImp.listar();
	}
	
	// URL: http://localhost:9002/MeserosWebService/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<String> guardarMesero(@RequestBody Meseros mesero) {
	    String response = meserosImp.guardar(mesero);

	    if (response.equals("idExiste")) {
	        return new ResponseEntity<>("El ID del mesero ya existe", HttpStatus.OK);
	    } else if (response.equals("nombreCompletoExiste")) {
	        return new ResponseEntity<>("No se guardó, el nombre completo del mesero ya existe", HttpStatus.OK);
	    } else if (response.equals("guardado")) {
	        return new ResponseEntity<>("Mesero guardado exitosamente", HttpStatus.CREATED);
	    } else {
	        return new ResponseEntity<>("Error al guardar el mesero", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	// URL: http://localhost:9002/MeserosWebService/buscar
	@PostMapping(path = "buscar")
	public Meseros buscar(@RequestBody Meseros meseros) {
		return meserosImp.buscar(meseros.getId());
	}
	
	// URL: http://localhost:9002/MeserosWebService/editar
	@PostMapping(path = "editar")
	public ResponseEntity<String> editar(@RequestBody Meseros meseros) {
	    boolean response = meserosImp.editar(meseros);

	    if (!response) {
	        return new ResponseEntity<>("Ese registro no existe", HttpStatus.NOT_FOUND);
	    } else {
	        return new ResponseEntity<>("Registro editado con éxito", HttpStatus.CREATED);
	    }
	}
	
	@PostMapping(path = "eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Meseros meseros) {
	    boolean response = meserosImp.eliminar(meseros.getId());

	    if (!response) {
	        return new ResponseEntity<>("Ese registro no existe", HttpStatus.NOT_FOUND);
	    } else {
	        return new ResponseEntity<>("Se elimino  con éxito", HttpStatus.OK);
	    }
	}

	
	

}
