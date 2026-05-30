package com.mvm.studies.api_docker.controller;

import com.mvm.studies.api_docker.dto.ClienteDTO;
import com.mvm.studies.api_docker.model.Cliente;
import com.mvm.studies.api_docker.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public Cliente criar(@RequestBody @Valid ClienteDTO dto) {

        return service.salvar(dto);
    }

    @GetMapping
    public List<Cliente> listar() {
        return service.listarTodos();
    }
}