package br.com.error.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "eusuario")
public class Usuario {
	
	@Id
	@Column(name = "email")
    private String email;
	@Column(name = "senha")
    private String password;
	@Column(name = "primeironome")
    private String primeiroNome;
	@Column(name = "ultimonome")
    private String ultimoNome;
	   
	public Usuario() {}

	public Usuario(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPrimeiroNome() {
		return primeiroNome;
	}
	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}
	public String getUltimoNome() {
		return ultimoNome;
	}
	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}
   
}
