package dev.ghonda.project.management.security.usecases.impl;

import dev.ghonda.project.management.security.services.JwtService;
import dev.ghonda.project.management.security.usecases.GenerateTokenUseCase;
import dev.ghonda.project.management.security.usecases.dto.TokenDetailPayload;
import dev.ghonda.project.management.security.usecases.dto.GenerateTokenPayload;
import dev.ghonda.project.management.shared.annotations.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

@Slf4j
@UseCase
@AllArgsConstructor
public class GenerateTokenUseCaseImpl implements GenerateTokenUseCase {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Override
    public TokenDetailPayload execute(final GenerateTokenPayload payload) {

        this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(payload.username(), payload.password())
        );

        final var user = this.userDetailsService.loadUserByUsername(payload.username());

        final var generatedToken = this.jwtService.generateToken(user, JwtService.TokenType.ACCESS);
        final var generatedRefreshToken = this.jwtService.generateToken(user, JwtService.TokenType.REFRESH);

        return TokenDetailPayload.builder()
            .token(generatedToken.token())
            .expiresIn(generatedToken.expiresAt())
            .refreshToken(generatedRefreshToken.token())
            .refreshTokenExpiresIn(generatedRefreshToken.expiresAt())
            .username(user.getUsername())
            .build();
    }

}
