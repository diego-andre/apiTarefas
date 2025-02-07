package com.api.application.usecases;

import java.util.List;

import com.api.application.gateways.TarefaGateway;
import com.api.domain.entity.Tarefa;

public class FindAllTarefa {

	private TarefaGateway tarefaGateway;

	public FindAllTarefa(TarefaGateway tarefaGateway) {
		this.tarefaGateway = tarefaGateway;
	}

	public List<Tarefa> findAll() {
		return tarefaGateway.findAll(); 
	}

}
