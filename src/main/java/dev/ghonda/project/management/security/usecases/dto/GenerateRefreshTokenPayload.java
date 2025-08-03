package dev.ghonda.project.management.security.usecases.dto;

import jakarta.validation.constraints.NotBlank;

public record GenerateRefreshTokenPayload(
    @NotBlank
    String refreshToken
) {
}
