package br.com.error.dto.erro;

import java.time.LocalDate;
import java.util.List;

import br.com.error.dto.solucao.SolucaoDTO;
import br.com.error.entity.sistema.Sistema;

public class ErrorDTO {
	
	private Integer cdErro;
	private String nmErro;
	private String deErro;
	private LocalDate dtCriacao;
	private String autor;
	private Sistema sistema;
	private List<SolucaoDTO> solucoes;

	public Integer getCdErro() {
		return cdErro;
	}
	public void setCdErro(Integer cdErro) {
		this.cdErro = cdErro;
	}
	public String getNmErro() {
		return nmErro;
	}
	public void setNmErro(String nmErro) {
		this.nmErro = nmErro;
	}
	public String getDeErro() {
		return deErro;
	}
	public void setDeErro(String deErro) {
		this.deErro = deErro;
	}
	public LocalDate getDtCriacao() {
		return dtCriacao;
	}
	public void setDtCriacao(LocalDate dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public List<SolucaoDTO> getSolucoes() {
		return solucoes;
	}
	public void setSolucoes(List<SolucaoDTO> solucoes) {
		this.solucoes = solucoes;
	}
	public Sistema getSistema() {
		return sistema;
	}
	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
	
}
