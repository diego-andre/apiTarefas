package com.api.application.usecases;

import com.api.application.gateways.TarefaGateway;
import com.api.domain.entity.Tarefa;

public class UpdateTarefa {
	
private TarefaGateway tarefaGateway;
	
	public UpdateTarefa(TarefaGateway tarefaGateway) {
		this.tarefaGateway = tarefaGateway;
	}

	public Tarefa updateById(Tarefa tarefa, Long id) {
		return tarefaGateway.updateById(tarefa, id);
	}

}
