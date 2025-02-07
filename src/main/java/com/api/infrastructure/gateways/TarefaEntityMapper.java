package com.api.infrastructure.gateways;

import com.api.domain.entity.Tarefa;
import com.api.infrastructure.persistence.TarefaEntity;

public class TarefaEntityMapper {
	
	TarefaEntity toEntity(Tarefa tarefa) {
		return new TarefaEntity(tarefa.id(),tarefa.titulo(), tarefa.descricao(),tarefa.status());
	}
	
	Tarefa ToDomainObj(TarefaEntity tarefa) {
		return new Tarefa(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getStatus());
	}

}
