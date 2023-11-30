package br.com.error.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.error.dto.solucao.DeleteSolucaoDTO;
import br.com.error.dto.solucao.InsertSolucaoDTO;
import br.com.error.dto.solucao.SolucaoDTO;
import br.com.error.dto.solucao.UpdateSolucaoDTO;
import br.com.error.service.SolucaoService;

@RestController
@RequestMapping("/solucao")
public class SolucaoController {

	@Autowired
	private SolucaoService solucaoService;
		
	@RequestMapping(
			value = "/all",
			method = RequestMethod.GET,
			produces = { "application/json", "application/xml"}  
	)
	public ResponseEntity<List<SolucaoDTO>> getSolucaos() {
		return new ResponseEntity<List<SolucaoDTO>>(solucaoService.getAllSolucaos(), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/save",
			method = RequestMethod.POST,
			produces = { "application/json", "application/xml"}  
	)
	public ResponseEntity<SolucaoDTO> saveSolucao(@RequestBody InsertSolucaoDTO dto) {
		return new ResponseEntity<SolucaoDTO>(solucaoService.save(dto), HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "/update",
			method = RequestMethod.PUT,
			produces = { "application/json", "application/xml"}  
	)
	public ResponseEntity<SolucaoDTO> updateSolucao(@RequestBody UpdateSolucaoDTO dto) {
		return new ResponseEntity<SolucaoDTO>(solucaoService.update(dto), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/delete",
			method = RequestMethod.DELETE,
			produces = { "application/json", "application/xml"}  
	)
	public ResponseEntity<?> deleteSolucao(@RequestBody DeleteSolucaoDTO dto) {
		solucaoService.delete(dto);
		return ResponseEntity.ok().build();
	}
	
}
