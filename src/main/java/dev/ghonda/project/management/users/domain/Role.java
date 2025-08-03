package dev.ghonda.project.management.users.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    ADMINISTRATOR("Administrador"),
    PROJECT_MANAGER("Gerente de Projeto"),
    MEMBER("Usuário Membro"),
    EXTERNAL("Usuário Externo");

    private final String label;

}
