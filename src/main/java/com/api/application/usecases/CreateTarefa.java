package com.api.application.usecases;

import com.api.application.gateways.TarefaGateway;
import com.api.domain.entity.Tarefa;

public class CreateTarefa {
	
	private TarefaGateway tarefaGateway;
	
	public CreateTarefa(TarefaGateway tarefaGateway) {
		this.tarefaGateway = tarefaGateway;
	}

	public Tarefa createTarefa(Tarefa tarefa) {
		return tarefaGateway.createTarefa(tarefa);
	}
}