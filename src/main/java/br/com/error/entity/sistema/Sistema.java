package br.com.error.entity.sistema;

import java.util.List;

import br.com.error.entity.erro.Erro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "esistema")
public class Sistema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cdsistema")
	private Integer cdSistema;
	@Column(name = "sgsistema")
	private String sgSistema;
	@Column(name = "desistema")
	private String deSistema;
	
	@OneToMany(mappedBy = "sistema")
	private List<Erro> erros;
	
	public Integer getCdSistema() {
		return cdSistema;
	}
	public void setCdSistema(Integer cdSistema) {
		this.cdSistema = cdSistema;
	}
	public String getSgSistema() {
		return sgSistema;
	}
	public void setSgSistema(String sgSistema) {
		this.sgSistema = sgSistema;
	}
	public String getDeSistema() {
		return deSistema;
	}
	public void setDeSistema(String deSistema) {
		this.deSistema = deSistema;
	}
	
}
