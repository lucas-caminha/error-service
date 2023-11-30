package br.com.error.dto.erro;

public class InsertErroDTO {
	
	private String nmErro;
	private String deErro;
	private String autor;
	private Integer cdSistema;
	
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
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Integer getCdSistema() {
		return cdSistema;
	}
	public void setCdSistema(Integer cdSistema) {
		this.cdSistema = cdSistema;
	}
	
}
