package com.api.infrastructure.controller;

import com.api.domain.entity.Tarefa;

public class TarefaDTOMapper {
	
	public TarefaRequestResponse toResponse(Tarefa tarefa) {
		return new TarefaRequestResponse(tarefa.id(),tarefa.titulo(), tarefa.descricao(), tarefa.status());
	}
	
	public Tarefa tarefa(TarefaRequestResponse request) {
		return new Tarefa(request.id(), request.titulo(), request.descricao(), request.status());
	}	

}
