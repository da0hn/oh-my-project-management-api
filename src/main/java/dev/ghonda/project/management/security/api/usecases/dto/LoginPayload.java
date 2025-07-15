package dev.ghonda.project.management.security.api.usecases.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginPayload(
    @NotBlank
    String username,
    @NotBlank
    String password
) {
}
