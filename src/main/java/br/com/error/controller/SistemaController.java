package br.com.error.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.error.dto.sistema.DeleteSistemaDTO;
import br.com.error.dto.sistema.InsertSistemaDTO;
import br.com.error.dto.sistema.SistemaDTO;
import br.com.error.dto.sistema.UpdateSistemaDTO;
import br.com.error.service.SistemaService;

@RestController
@RequestMapping("/sistema")
public class SistemaController {

	@Autowired
	private SistemaService sistemaService;
		
	@RequestMapping(
			value = "/all",
			method = RequestMethod.GET,
			produces = { "application/json", "application/xml"}  
	)
	public ResponseEntity<List<SistemaDTO>> getSistemas() {
		return new ResponseEntity<List<SistemaDTO>>(sistemaService.getAllSistemas(), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/save",
			method = RequestMethod.POST,
			produces = { "application/json", "application/xml"}  
	)
	public ResponseEntity<SistemaDTO> saveSistema(@RequestBody InsertSistemaDTO dto) {
		return new ResponseEntity<SistemaDTO>(sistemaService.save(dto), HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "/update",
			method = RequestMethod.PUT,
			produces = { "application/json", "application/xml"}  
	)
	public ResponseEntity<SistemaDTO> updateSistema(@RequestBody UpdateSistemaDTO dto) {
		return new ResponseEntity<SistemaDTO>(sistemaService.update(dto), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/delete",
			method = RequestMethod.DELETE,
			produces = { "application/json", "application/xml"}  
	)
	public ResponseEntity<?> deleteSistema(@RequestBody DeleteSistemaDTO dto) {
		sistemaService.delete(dto);
		return ResponseEntity.ok().build();
	}
	
}
