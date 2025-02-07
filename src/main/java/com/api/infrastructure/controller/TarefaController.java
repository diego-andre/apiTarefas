package com.api.infrastructure.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.application.usecases.CreateTarefa;
import com.api.application.usecases.DeleteTarefa;
import com.api.application.usecases.FindAllTarefa;
import com.api.application.usecases.UpdateTarefa;
import com.api.domain.entity.Tarefa;

@RestController
@RequestMapping("tarefas")
public class TarefaController {
	
	private final CreateTarefa createTarefa;
	private final FindAllTarefa  findAllTarefa;
	private final DeleteTarefa  deleteTarefa;
	private final UpdateTarefa  updateTarefa;
	private final TarefaDTOMapper tarefaDTOMapper;

	public TarefaController(CreateTarefa createTarefa, TarefaDTOMapper tarefaDTOMapper, FindAllTarefa  findAllTarefa, DeleteTarefa  deleteTarefa, UpdateTarefa  updateTarefa) {
		this.createTarefa = createTarefa;
		this.tarefaDTOMapper = tarefaDTOMapper;
		this.findAllTarefa = findAllTarefa;
		this.deleteTarefa = deleteTarefa;
		this.updateTarefa = updateTarefa;
	}
	
	@PostMapping
	TarefaRequestResponse create(@RequestBody TarefaRequestResponse request) {		
		Tarefa tarefaObj = tarefaDTOMapper.tarefa(request);
		Tarefa tarefa = createTarefa.createTarefa(tarefaObj);		
		return tarefaDTOMapper.toResponse(tarefa);
	}
	
	@GetMapping
	List<Tarefa> listAllTarefas() {
		return findAllTarefa.findAll();
	}
	
	@DeleteMapping("{id}")
	void deleteBookById(@PathVariable(value = "id") Long id) {
		deleteTarefa.deleteById(id);
	}
	
	@PutMapping("/{id}")
	TarefaRequestResponse updateBookById(@PathVariable(value = "id") Long id, @RequestBody TarefaRequestResponse request) {
		Tarefa tarefaObj = tarefaDTOMapper.tarefa(request);		
		Tarefa tarefa = updateTarefa.updateById(tarefaObj, id);
		return tarefaDTOMapper.toResponse(tarefa);
	}
	

}
