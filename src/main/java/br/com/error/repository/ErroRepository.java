package br.com.error.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.error.entity.erro.Erro;

public interface ErroRepository extends JpaRepository<Erro, Integer> {

}
