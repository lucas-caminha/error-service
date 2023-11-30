package br.com.error.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.error.dto.sistema.DeleteSistemaDTO;
import br.com.error.dto.sistema.InsertSistemaDTO;
import br.com.error.dto.sistema.SistemaDTO;
import br.com.error.dto.sistema.UpdateSistemaDTO;
import br.com.error.entity.sistema.Sistema;
import br.com.error.exception.EmptyException;
import br.com.error.exception.NotFoundException;
import br.com.error.repository.SistemaRepository;
import br.com.error.utils.Validador;

@Service
public class SistemaService {
	
	@Autowired
	private SistemaRepository sistemaRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	public SistemaDTO save(InsertSistemaDTO dto) {
		if(Validador.isNullOrEmpty(dto.getSgSistema()) || Validador.isNullOrEmpty(dto.getDeSistema()))
			throw new EmptyException("Campos obrigatórios não foram preenchidos.");
		
		return modelMapper.map(saveSistema(dto), SistemaDTO.class);
	}

	public List<SistemaDTO> getAllSistemas() {
		List<Sistema> Sistemas = sistemaRepository.findAll();
	
		if(!Sistemas.isEmpty())
			return Sistemas.stream().map(Sistema -> modelMapper.map(Sistema, SistemaDTO.class)).collect(Collectors.toList());
		
		return new ArrayList<SistemaDTO>();
	}

	public SistemaDTO update(UpdateSistemaDTO dto) {
		if(Validador.isNullOrEmpty(dto.getCdSistema()))
			throw new EmptyException("Campos obrigatórios não foram preenchidos.");
		
		return modelMapper.map(updateSistema(dto), SistemaDTO.class);
	}

	public void delete(DeleteSistemaDTO dto) {
		if(Validador.isNullOrEmpty(dto.getCdSistema()))
			throw new EmptyException("Campos obrigatórios não foram preenchidos.");
		
		Optional<Sistema> SistemaOptional = findSistemaById(dto.getCdSistema());
		
		if(SistemaOptional.isEmpty())
			throw new NotFoundException("Sistema não encontrado.");
			
		sistemaRepository.delete(SistemaOptional.get());
	}
	
	public Optional<Sistema> findSistemaById(Integer cdSistema) {
		return sistemaRepository.findById(cdSistema);
	}
		
	private Sistema saveSistema(InsertSistemaDTO dto) {
		Sistema entity = modelMapper.map(dto, Sistema.class);
		return sistemaRepository.save(entity);
	}
	
	private Sistema updateSistema(UpdateSistemaDTO dto) {
		if(Validador.isNullOrEmpty(dto.getCdSistema()))
			throw new EmptyException("Campos obrigatórios não foram preenchidos.");
		
		Optional<Sistema> sistemaOptional = findSistemaById(dto.getCdSistema());
		
		if(sistemaOptional.isEmpty())
			throw new NotFoundException("Sistema não encontrado.");
		
		Sistema Sistema = sistemaOptional.get();	
		Sistema.setSgSistema(dto.getSgSistema());
		Sistema.setDeSistema(dto.getDeSistema());
		
		return sistemaRepository.save(Sistema);
	}
	
}
