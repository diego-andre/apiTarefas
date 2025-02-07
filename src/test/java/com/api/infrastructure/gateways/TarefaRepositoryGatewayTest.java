package com.api.infrastructure.gateways;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import com.api.domain.entity.Tarefa;
import com.api.infrastructure.persistence.Status;
import com.api.infrastructure.persistence.TarefaRepository;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class TarefaRepositoryGatewayTest {
		
	@Mock
	TarefaRepositoryGateway tarefaRepositoryGateway;
	
	@Mock
	TarefaRepository tarefaRepository;	
	
	@Mock
	TarefaEntityMapper tarefaEntityMapper;
	
	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

	@Test
	@DisplayName("Tarefa cadastrada com sucesso!")
	void createTarefaSucesso() {
		Tarefa tarefa = new Tarefa(null, "Teste", "sucesso", Status.PENDENTE);

		when(tarefaRepositoryGateway.createTarefa(tarefa)).thenReturn(tarefa);
		Tarefa tarefaNew = tarefaRepositoryGateway.createTarefa(tarefa);

		assertThat(tarefaNew).isEqualTo(tarefa);
	}
	
	@Test
	@DisplayName("Erro ao cadastrar Tarefa!")
	void createTarefaErro() {	
		Tarefa tarefa = new Tarefa(null, null, null, null);
		
		when(tarefaRepositoryGateway.createTarefa(tarefa)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> tarefaRepositoryGateway.createTarefa(tarefa)).isInstanceOf(RuntimeException.class);		
	}
	
	@Test
	@DisplayName("Tarefas listadas com sucesso!")
	void listTarefaSucesso() {
		Tarefa tarefa = new Tarefa(null, "Teste", "sucesso", Status.PENDENTE);
		when(tarefaRepositoryGateway.findAll()).thenReturn(List.of(tarefa));

		List<Tarefa> tarefas = tarefaRepositoryGateway.findAll();
		assertThat(tarefas).isNotEmpty();
		assertThat(tarefas).hasSize(1);
	}

    @Test
    @DisplayName("Não há Tarefas!")
	void listTarefaErro() {
		when(tarefaRepositoryGateway.findAll()).thenReturn(List.of());

		List<Tarefa> tarefas = tarefaRepositoryGateway.findAll();

		assertThat(tarefas).isEmpty();
	}

    @Test
    @DisplayName("Tarefa removida com sucesso!")
    void removeTarefaIDExistente() {
        assertThatCode(() -> tarefaRepositoryGateway.deleteById(1L)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Erro ao remover Tarefa!")
    void removeTarefaIDInexistente() {
        doThrow(new RuntimeException()).when(tarefaRepositoryGateway).deleteById(99L);

        assertThatThrownBy(() -> tarefaRepositoryGateway.deleteById(99L)).isInstanceOf(RuntimeException.class);
    }
    
    @Test
    @DisplayName("Tarefa editada com sucesso!")
    void editarTarefaSucesso() {    	
        Tarefa novaTarefa = new Tarefa(1L, "Teste Editado", "editando", Status.CONCLUIDO);
        
        when(tarefaRepositoryGateway.updateById(novaTarefa, 1L)).thenReturn(novaTarefa);
        
        Tarefa tarefaEditada = tarefaRepositoryGateway.updateById(novaTarefa, 1L);
        
        assertNotNull(tarefaEditada);
        assertEquals("Teste Editado", tarefaEditada.titulo());
        assertEquals("editando", tarefaEditada.descricao());
        
        verify(tarefaRepositoryGateway, times(1)).updateById(novaTarefa, 1L);
    }    
    
}
