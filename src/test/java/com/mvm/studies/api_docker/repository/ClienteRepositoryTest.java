package com.mvm.studies.api_docker.repository;


import com.mvm.studies.api_docker.model.Cliente;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository repository;

    @Test
    void deveSalvarCliente() {

        Cliente cliente = new Cliente();

        cliente.setNome("João");
        cliente.setEmail("joao@email.com");

        Cliente salvo = repository.save(cliente);

        assertNotNull(salvo.getId());
    }

    @Test
    void deveEncontrarEmailExistente() {

        Cliente cliente = new Cliente();

        cliente.setNome("João");
        cliente.setEmail("joao@email.com");

        repository.save(cliente);

        boolean existe =
                repository.existsByEmail(
                        "joao@email.com"
                );

        assertTrue(existe);
    }
}