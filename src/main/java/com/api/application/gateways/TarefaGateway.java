package com.api.application.gateways;

import java.util.List;

import com.api.domain.entity.Tarefa;

public interface TarefaGateway {
	
	Tarefa createTarefa(Tarefa tarefa);
	
	List<Tarefa> findAll();
	
	void deleteById(Long id);
	
	Tarefa updateById(Tarefa tarefa, Long id);
}