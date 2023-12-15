package br.com.error.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.error.entity.user.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	public Optional<Usuario> findUsuarioByEmail(String email);

}
