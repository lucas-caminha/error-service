package br.com.error.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.error.dto.solucao.DeleteSolucaoDTO;
import br.com.error.dto.solucao.InsertSolucaoDTO;
import br.com.error.dto.solucao.SolucaoDTO;
import br.com.error.dto.solucao.UpdateSolucaoDTO;
import br.com.error.entity.erro.Erro;
import br.com.error.entity.solucao.Solucao;
import br.com.error.exception.EmptyException;
import br.com.error.exception.NotFoundException;
import br.com.error.repository.SolucaoRepository;
import br.com.error.utils.Validador;

@Service
public class SolucaoService {
	
	@Autowired
	private SolucaoRepository solucaoRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	public SolucaoDTO save(InsertSolucaoDTO dto) {
		if(Validador.isNullOrEmpty(dto.getCdErro()) || Validador.isNullOrEmpty(dto.getDeSolucao()))
			throw new EmptyException("Campos obrigatórios não foram preenchidos.");
		
		return modelMapper.map(saveSolucao(dto), SolucaoDTO.class);
	}

	public List<SolucaoDTO> getAllSolucaos() {
		List<Solucao> Solucaos = solucaoRepository.findAll();
	
		if(!Solucaos.isEmpty())
			return Solucaos.stream().map(Solucao -> modelMapper.map(Solucao, SolucaoDTO.class)).collect(Collectors.toList());
		
		return new ArrayList<SolucaoDTO>();
	}

	public SolucaoDTO update(UpdateSolucaoDTO dto) {
		if(Validador.isNullOrEmpty(dto.getCdSolucao()))
			throw new EmptyException("Campos obrigatórios não foram preenchidos.");
		
		return modelMapper.map(updateSolucao(dto), SolucaoDTO.class);
	}

	public void delete(DeleteSolucaoDTO dto) {
		if(Validador.isNullOrEmpty(dto.getCdSolucao()))
			throw new EmptyException("Campos obrigatórios não foram preenchidos.");
		
		Optional<Solucao> SolucaoOptional = findSolucaoById(dto.getCdSolucao());
		
		if(SolucaoOptional.isEmpty())
			throw new NotFoundException("Solucao não encontrada.");
			
		solucaoRepository.delete(SolucaoOptional.get());
	}
	
	public Optional<Solucao> findSolucaoById(Integer cdSolucao) {
		return solucaoRepository.findById(cdSolucao);
	}
		
	private Solucao saveSolucao(InsertSolucaoDTO dto) {
		Solucao entity = modelMapper.map(dto, Solucao.class);
		return solucaoRepository.save(entity);
	}
	
	private Solucao updateSolucao(UpdateSolucaoDTO dto) {
		Optional<Solucao> SolucaoOptional = findSolucaoById(dto.getCdSolucao());
		
		if(SolucaoOptional.isEmpty())
			throw new NotFoundException("Solucao não encontrada.");
		
		Solucao Solucao = SolucaoOptional.get();	
		Solucao.setDeSolucao(dto.getDeSolucao());
		
		return solucaoRepository.save(Solucao);
	}

	public List<Solucao> findSolucoesByErro(Erro erro) {
		if(Validador.isNullOrEmpty(erro.getCdErro()))
			throw new EmptyException("Campos obrigatórios não foram preenchidos.");
		
		List<Solucao> solucoes = solucaoRepository.findByErro(erro);
		if(!solucoes.isEmpty())
			return solucoes;
		 
		return new ArrayList<Solucao>();
	}
	
}
