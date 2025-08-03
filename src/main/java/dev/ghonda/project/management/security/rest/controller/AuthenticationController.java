package dev.ghonda.project.management.security.rest.controller;

import dev.ghonda.project.management.security.usecases.GenerateRefreshTokenUseCase;
import dev.ghonda.project.management.security.usecases.GenerateTokenUseCase;
import dev.ghonda.project.management.security.usecases.dto.GenerateRefreshTokenPayload;
import dev.ghonda.project.management.security.usecases.dto.TokenDetailPayload;
import dev.ghonda.project.management.security.usecases.dto.GenerateTokenPayload;
import dev.ghonda.project.management.shared.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/auth")
public class AuthenticationController {

    private final GenerateTokenUseCase generateTokenUseCase;
    private final GenerateRefreshTokenUseCase generateRefreshTokenUseCase;

    @PostMapping("/token")
    public ResponseEntity<ApiResponse<TokenDetailPayload>> generateToken(@RequestBody @Valid final GenerateTokenPayload generateTokenPayload) {
        final var output = this.generateTokenUseCase.execute(generateTokenPayload);
        return ResponseEntity.ok(ApiResponse.of(output));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<TokenDetailPayload>> refreshToken(@RequestBody @Valid final GenerateRefreshTokenPayload generateRefreshTokenPayload) {
        final var output = this.generateRefreshTokenUseCase.execute(generateRefreshTokenPayload);
        return ResponseEntity.ok(ApiResponse.of(output));
    }

}
