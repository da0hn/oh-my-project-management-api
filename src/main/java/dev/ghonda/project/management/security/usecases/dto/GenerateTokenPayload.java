package dev.ghonda.project.management.security.usecases.dto;

import jakarta.validation.constraints.NotBlank;

public record GenerateTokenPayload(
    @NotBlank
    String username,
    @NotBlank
    String password
) {
}
