package com.mvm.studies.api_docker.repository;

import com.mvm.studies.api_docker.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository
        extends JpaRepository<Cliente, Long> {

    boolean existsByEmail(String email);
}
