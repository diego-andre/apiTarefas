package com.api.infrastructure.gateways;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.api.application.gateways.TarefaGateway;
import com.api.domain.entity.Tarefa;
import com.api.infrastructure.persistence.Status;
import com.api.infrastructure.persistence.TarefaEntity;
import com.api.infrastructure.persistence.TarefaRepository;

public class TarefaRepositoryGateway implements TarefaGateway {
	
	private final TarefaRepository tarefaRepository;
	private final TarefaEntityMapper tarefaEntityMapper;
	
	public TarefaRepositoryGateway(TarefaRepository tarefaRepository, TarefaEntityMapper tarefaEntityMapper) {
		this.tarefaRepository = tarefaRepository;
		this.tarefaEntityMapper = tarefaEntityMapper;
	}
	
	@Override
	public Tarefa createTarefa(Tarefa tarefaDomainObj) {		
		TarefaEntity tarefaEntity = tarefaEntityMapper.toEntity(tarefaDomainObj);
		tarefaEntity.setDataCriacao(new Date());
		tarefaEntity.setStatus(Status.PENDENTE);
		TarefaEntity saveObj = tarefaRepository.save(tarefaEntity);
		return tarefaEntityMapper.ToDomainObj(saveObj);
	}

	@Override
	public List<Tarefa> findAll() {	
		 Iterable<TarefaEntity> listAll = tarefaRepository.findAll();
		 List<Tarefa> list = StreamSupport.stream(listAll.spliterator(), false)
	                .map(entity -> tarefaEntityMapper.ToDomainObj(entity))
	                .collect(Collectors.toList());
		 return list;			
	}
	
	@Override
	public void deleteById(Long id) {				
		tarefaRepository.deleteById(id);
	}
	
	@Override
	public Tarefa updateById(Tarefa tarefa, Long id) {
		TarefaEntity tarefaEntity = tarefaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));
		
		tarefaEntity.setId(id);
		tarefaEntity.setTitulo(tarefa.titulo());
		tarefaEntity.setDescricao(tarefa.descricao());
		tarefaEntity.setStatus(tarefa.status());
		tarefaEntity.setDataCriacao(tarefaEntity.getDataCriacao());

		TarefaEntity saveObj = tarefaRepository.save(tarefaEntity);

		return tarefaEntityMapper.ToDomainObj(saveObj);
	}
}