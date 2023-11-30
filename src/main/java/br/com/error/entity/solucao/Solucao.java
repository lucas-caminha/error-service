package br.com.error.entity.solucao;

import br.com.error.entity.erro.Erro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "esolucao")
public class Solucao {
	
	@Id
	@Column(name = "cdsolucao")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cdSolucao;
	@Column(name = "desolucao")
	private String deSolucao;
	@ManyToOne
    @JoinColumn(name="cderro")
	private Erro erro;
	
	public Solucao() {}
	
	public String getDeSolucao() {
		return deSolucao;
	}
	
	public void setDeSolucao(String deSolucao) {
		this.deSolucao = deSolucao;
	}

	public Integer getCdSolucao() {
		return cdSolucao;
	}

	public void setCdSolucao(Integer cdSolucao) {
		this.cdSolucao = cdSolucao;
	}

	public Erro getErro() {
		return erro;
	}

	public void setErro(Erro erro) {
		this.erro = erro;
	}
	
}
