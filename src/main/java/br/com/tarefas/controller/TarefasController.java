package br.com.tarefas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.domain.Estado;
import br.com.tarefas.domain.Tarefas;
import br.com.tarefas.repository.TarefasRepository;

@RestController
public class TarefasController {
	
	@Autowired
	private TarefasRepository tr;
	
	@GetMapping("/tarefas/listar")
	public List<Tarefas> listar() {
		return tr.findAll();
	}
	
	@GetMapping("/tarefas/listar/{titulo}")
	public List<Tarefas> listarT(@PathVariable String titulo) {
		
		return tr.findByTitulo(titulo);
	}
	
	@GetMapping("/tarefas/listar/Aberto")
	public List<Tarefas> getStringToMode() {
		return tr.findByEstado(Estado.Aberto);
	   
	}
	
	
	@PostMapping("/tarefas/cadastrar")
	public String cadastro(@RequestBody Tarefas dados) {
		String msg = "Cadastrou!";
		
		Tarefas titulo = tr.save(dados);
		return msg;
	}
	
	@PutMapping("/tarefas/atualizar/{id}")
	public String atualizar(@PathVariable Integer id, @RequestBody Tarefas tarefa) {
		String msg = "";
		Optional<Tarefas> t = tr.findById(id);
		
			if(t.isPresent()) {
				tarefa.setIdtarefas(id);
				tr.save(tarefa);
				msg = "Tarefa atualizada";
			}
			else {
				msg = "Tarefa não encontrada";
			}
		return msg;
	}
}
