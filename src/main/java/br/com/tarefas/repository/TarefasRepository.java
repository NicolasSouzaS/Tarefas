package br.com.tarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tarefas.domain.Estado;
import br.com.tarefas.domain.Tarefas;

public interface TarefasRepository extends JpaRepository<Tarefas, Integer>{

	List<Tarefas> findAll();
	List<Tarefas> findByTitulo(String titulo);
	List<Tarefas> findByEstado(Estado estado);
}
