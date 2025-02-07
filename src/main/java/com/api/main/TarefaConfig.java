package com.api.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.application.gateways.TarefaGateway;
import com.api.application.usecases.CreateTarefa;
import com.api.application.usecases.DeleteTarefa;
import com.api.application.usecases.FindAllTarefa;
import com.api.application.usecases.UpdateTarefa;
import com.api.infrastructure.controller.TarefaDTOMapper;
import com.api.infrastructure.gateways.TarefaEntityMapper;
import com.api.infrastructure.gateways.TarefaRepositoryGateway;
import com.api.infrastructure.persistence.TarefaRepository;

@Configuration
public class TarefaConfig {
	
	@Bean
	CreateTarefa createTarefaInterator(TarefaGateway tarefaGateway) {
		return new CreateTarefa(tarefaGateway);
	}
	
	@Bean
	TarefaGateway tarefaGateway(TarefaRepository tarefaRepository, TarefaEntityMapper tarefaEntityMapper) {
		return new TarefaRepositoryGateway(tarefaRepository, tarefaEntityMapper);
	}
	
	@Bean
	TarefaEntityMapper tarefaEntityMapper() {
		return new TarefaEntityMapper();
	}
	
	@Bean
	TarefaDTOMapper tarefaDTOMapper() {
		return new TarefaDTOMapper();
	}
	
	@Bean
	FindAllTarefa findAllTarefaInterator(TarefaGateway tarefaGateway) {
		return new FindAllTarefa(tarefaGateway);
	}
			
	@Bean
	UpdateTarefa updateTarefaInterator(TarefaGateway tarefaGateway) {
		return new UpdateTarefa(tarefaGateway);
	}
	
	@Bean
	DeleteTarefa deleteTarefaInterator(TarefaGateway tarefaGateway) {
		return new DeleteTarefa(tarefaGateway);
	}

}
