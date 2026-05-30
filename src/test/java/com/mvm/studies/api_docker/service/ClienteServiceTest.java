package com.mvm.studies.api_docker.service;

import com.mvm.studies.api_docker.dto.ClienteDTO;
import com.mvm.studies.api_docker.model.Cliente;
import com.mvm.studies.api_docker.repository.ClienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarCliente() {

        ClienteDTO dto = new ClienteDTO();
        dto.setNome("João");
        dto.setEmail("joao@email.com");

        Cliente clienteSalvo = new Cliente(
                1L,
                "João",
                "joao@email.com"
        );

        when(repository.existsByEmail(dto.getEmail()))
                .thenReturn(false);

        when(repository.save(any(Cliente.class)))
                .thenReturn(clienteSalvo);

        Cliente resultado = service.salvar(dto);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());

        verify(repository).save(any(Cliente.class));
    }

    @Test
    void deveLancarExcecaoQuandoEmailExistir() {

        ClienteDTO dto = new ClienteDTO();
        dto.setNome("João");
        dto.setEmail("joao@email.com");

        when(repository.existsByEmail(dto.getEmail()))
                .thenReturn(true);

        RuntimeException exception =
                assertThrows(RuntimeException.class,
                        () -> service.salvar(dto));

        assertEquals(
                "E-mail já cadastrado",
                exception.getMessage()
        );
    }

    @Test
    void deveListarClientes() {

        List<Cliente> clientes = List.of(
                new Cliente(1L, "João", "joao@email.com"),
                new Cliente(2L, "Maria", "maria@email.com")
        );

        when(repository.findAll()).thenReturn(clientes);

        List<Cliente> resultado = service.listarTodos();

        assertEquals(2, resultado.size());

        verify(repository).findAll();
    }
}
