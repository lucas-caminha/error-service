package br.com.error.entity.erro;

import java.time.LocalDate;
import java.util.List;

import br.com.error.entity.sistema.Sistema;
import br.com.error.entity.solucao.Solucao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "eerro")
public class Erro {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cderro")
	private Integer cdErro;
	@Column(name = "nmerro")
	private String nmErro;
	@Column(name = "deerro")
	private String deErro;
	@Column(name = "dtcriacao")
	private LocalDate dtCriacao;
	@Column(name = "autor")
	private String autor;
	
    @OneToMany(mappedBy = "erro")
	private List<Solucao> solucoes;
    @ManyToOne
    @JoinColumn(name="cdsistema")
    private Sistema sistema;
		
	public Erro() {}
	
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
	public List<Solucao> getSolucoes() {
		return solucoes;
	}
	public void setSolucoes(List<Solucao> solucoes) {
		this.solucoes = solucoes;
	}
	public Sistema getSistema() {
		return sistema;
	}
	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
	
}
