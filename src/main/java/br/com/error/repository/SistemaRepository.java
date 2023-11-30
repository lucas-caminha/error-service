package br.com.error.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.error.entity.sistema.Sistema;

public interface SistemaRepository extends JpaRepository<Sistema, Integer> {

}
