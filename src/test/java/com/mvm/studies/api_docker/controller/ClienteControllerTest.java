package com.mvm.studies.api_docker.controller;


import com.mvm.studies.api_docker.model.Cliente;
import com.mvm.studies.api_docker.service.ClienteService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService service;

    @Test
    void deveRetornarListaDeClientes() throws Exception {

        List<Cliente> clientes = List.of(
                new Cliente(
                        1L,
                        "João",
                        "joao@email.com"
                )
        );

        when(service.listarTodos())
                .thenReturn(clientes);

        mockMvc.perform(get("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].nome")
                        .value("João"))

                .andExpect(jsonPath("$[0].email")
                        .value("joao@email.com"));
    }
}