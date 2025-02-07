package com.api.infrastructure.controller;

import com.api.infrastructure.persistence.Status;

public record TarefaRequestResponse(Long id, String titulo, String descricao, Status status) {

}
