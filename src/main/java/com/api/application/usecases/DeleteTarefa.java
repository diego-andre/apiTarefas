package com.api.application.usecases;

import com.api.application.gateways.TarefaGateway;

public class DeleteTarefa {
	
	private TarefaGateway tarefaGateway;

	public DeleteTarefa(TarefaGateway tarefaGateway) {
		this.tarefaGateway = tarefaGateway;
	}

	public void deleteById(Long id) {
		tarefaGateway.deleteById(id);
	}
}
