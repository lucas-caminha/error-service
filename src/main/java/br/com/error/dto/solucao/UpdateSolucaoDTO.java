package br.com.error.dto.solucao;

public class UpdateSolucaoDTO {
	
	private Integer cdSolucao;
	private String deSolucao;
	private Integer cdErro;
	
	public Integer getCdSolucao() {
		return cdSolucao;
	}
	public void setCdSolucao(Integer cdSolucao) {
		this.cdSolucao = cdSolucao;
	}
	public String getDeSolucao() {
		return deSolucao;
	}
	public void setDeSolucao(String deSolucao) {
		this.deSolucao = deSolucao;
	}
	public Integer getCdErro() {
		return cdErro;
	}
	public void setCdErro(Integer cdErro) {
		this.cdErro = cdErro;
	}

}
