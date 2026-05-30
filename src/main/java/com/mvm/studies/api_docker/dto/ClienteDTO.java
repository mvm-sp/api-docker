package com.mvm.studies.api_docker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    @NotBlank
    private String nome;

    @Email
    private String email;

    // getters e setters
}