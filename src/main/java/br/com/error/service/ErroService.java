package br.com.error.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.error.dto.erro.DeleteErroDTO;
import br.com.error.dto.erro.ErrorDTO;
import br.com.error.dto.erro.FindErroDTO;
import br.com.error.dto.erro.InsertErroDTO;
import br.com.error.dto.erro.UpdateErroDTO;
import br.com.error.entity.erro.Erro;
import br.com.error.entity.sistema.Sistema;
import br.com.error.exception.EmptyException;
import br.com.error.exception.NotFoundException;
import br.com.error.repository.ErroRepository;
import br.com.error.utils.Validador;

@Service
public class ErroService {
	
	@Autowired
	private ErroRepository erroRepository;	
	@Autowired
	private SistemaService sistemaService;	
	@Autowired
    private ModelMapper modelMapper;
	@Autowired
	private SolucaoService solucaoService;
	
	public ErrorDTO save(InsertErroDTO dto) {
		if(Validador.isNullOrEmpty(dto.getNmErro()) || Validador.isNullOrEmpty(dto.getDeErro()) || Validador.isNullOrEmpty(dto.getAutor()))
			throw new EmptyException("Campos obrigatórios não foram preenchidos.");
		
		return modelMapper.map(saveErro(dto), ErrorDTO.class);
	}

	public List<ErrorDTO> getAllErros() {
		List<Erro> erros = erroRepository.findAll();
	
		if(!erros.isEmpty())
			return erros.stream().map(erro -> modelMapper.map(erro, ErrorDTO.class)).collect(Collectors.toList());
		
		return new ArrayList<ErrorDTO>();
	}

	public ErrorDTO update(UpdateErroDTO dto) {
		if(Validador.isNullOrEmpty(dto.getCdErro()))
			throw new EmptyException("Campos obrigatórios não foram preenchidos.");
		
		return modelMapper.map(updateErro(dto), ErrorDTO.class);
	}

	public void delete(DeleteErroDTO dto) {		
		if(Validador.isNullOrEmpty(dto.getCdErro()))
			throw new EmptyException("Campos obrigatórios não foram preenchidos.");
		
		Optional<Erro> erroOptional = findErroById(dto.getCdErro());
		
		if(erroOptional.isEmpty())
			throw new NotFoundException("Erro não encontrado.");
			
		erroRepository.delete(erroOptional.get());
	}
		
	public Optional<Erro> findErroById(Integer cdErro) {
		return erroRepository.findById(cdErro);
	}
		
	private Erro saveErro(InsertErroDTO dto) {
		Erro entity = modelMapper.map(dto, Erro.class);
	
		Optional<Sistema> sistema = sistemaService.findSistemaById(dto.getCdSistema());
		if(sistema.isEmpty())
			throw new NotFoundException("Sistema não encontrado.");
		
		entity.setDtCriacao(LocalDate.now());
		entity.setSistema(sistema.get());
		
		return erroRepository.save(entity);
	}
	
	private Erro updateErro(UpdateErroDTO dto) {
		if(Validador.isNullOrEmpty(dto.getCdErro()))
			throw new EmptyException("Campos obrigatórios não foram preenchidos.");
		
		Optional<Erro> erroOptional = findErroById(dto.getCdErro());
		
		if(erroOptional.isEmpty())
			throw new NotFoundException("Erro não encontrado.");
		
		Erro erro = erroOptional.get();	
		erro.setNmErro(dto.getNmErro());
		erro.setDeErro(dto.getDeErro());
		
		return erroRepository.save(erro);
	}

	public ErrorDTO find(FindErroDTO dto) {
		if(Validador.isNullOrEmpty(dto.getCdErro()))
			throw new EmptyException("Campos obrigatórios não foram preenchidos.");
		
		Optional<Erro> erroOptional = erroRepository.findById(dto.getCdErro());
		
		if(erroOptional.isEmpty())
			throw new NotFoundException("Erro não encontrado.");
		
		Erro erro = erroOptional.get();		
		erro.setSolucoes(solucaoService.findSolucoesByErro(erro));
		
		ErrorDTO erroDTO = modelMapper.map(erro, ErrorDTO.class);
		 
		return erroDTO;
	}
	
}
