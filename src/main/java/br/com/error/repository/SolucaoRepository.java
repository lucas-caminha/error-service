package br.com.error.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.error.entity.erro.Erro;
import br.com.error.entity.solucao.Solucao;

public interface SolucaoRepository extends JpaRepository<Solucao, Integer> {
	
	List<Solucao> findByErro(Erro erro);

}
