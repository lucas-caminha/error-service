package br.com.error.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.error.dto.erro.DeleteErroDTO;
import br.com.error.dto.erro.ErrorDTO;
import br.com.error.dto.erro.FindErroDTO;
import br.com.error.dto.erro.InsertErroDTO;
import br.com.error.dto.erro.UpdateErroDTO;
import br.com.error.service.ErroService;

@RestController
@RequestMapping("/erro")
public class ErroController {

	@Autowired
	private ErroService erroService;
		
	@RequestMapping(
			value = "/all",
			method = RequestMethod.GET,
			produces = { "application/json", "application/xml"}  
	)
	public ResponseEntity<List<ErrorDTO>> getErros() {
		return new ResponseEntity<List<ErrorDTO>>(erroService.getAllErros(), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/save",
			method = RequestMethod.POST,
			produces = { "application/json", "application/xml"}  
	)
	public ResponseEntity<ErrorDTO> saveErro(@RequestBody InsertErroDTO dto) {
		return new ResponseEntity<ErrorDTO>(erroService.save(dto), HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "/update",
			method = RequestMethod.PUT,
			produces = { "application/json", "application/xml"}  
	)
	public ResponseEntity<ErrorDTO> updateErro(@RequestBody UpdateErroDTO dto) {
		return new ResponseEntity<ErrorDTO>(erroService.update(dto), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/delete",
			method = RequestMethod.DELETE,
			produces = { "application/json", "application/xml"}  
	)
	public ResponseEntity<?> deleteErro(@RequestBody DeleteErroDTO dto) {
		erroService.delete(dto);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(
			value = "/find",
			method = RequestMethod.GET,
			produces = { "application/json", "application/xml"}  
	)
	public ResponseEntity<ErrorDTO> findErro(@RequestBody FindErroDTO dto) {
		return new ResponseEntity<ErrorDTO>(erroService.find(dto), HttpStatus.OK);
	}
}
