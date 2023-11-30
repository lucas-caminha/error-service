package br.com.error.dto.erro;

public class UpdateErroDTO {
	
	private Integer cdErro;
	private String nmErro;
	private String deErro;
	
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
	public Integer getCdErro() {
		return cdErro;
	}
	public void setCdErro(Integer cdErro) {
		this.cdErro = cdErro;
	}
	
}
