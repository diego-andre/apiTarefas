package com.api.domain.entity;

import com.api.infrastructure.persistence.Status;

public record Tarefa(Long id, String titulo, String descricao, Status status) {

}
