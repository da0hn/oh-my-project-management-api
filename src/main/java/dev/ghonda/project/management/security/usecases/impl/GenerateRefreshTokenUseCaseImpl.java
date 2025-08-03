package dev.ghonda.project.management.security.usecases.impl;

import dev.ghonda.project.management.security.exceptions.RefreshTokenInvalidException;
import dev.ghonda.project.management.security.services.JwtService;
import dev.ghonda.project.management.security.usecases.GenerateRefreshTokenUseCase;
import dev.ghonda.project.management.security.usecases.SearchUserDetailsUseCase;
import dev.ghonda.project.management.security.usecases.dto.GenerateRefreshTokenPayload;
import dev.ghonda.project.management.security.usecases.dto.TokenDetailPayload;
import dev.ghonda.project.management.shared.annotations.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class GenerateRefreshTokenUseCaseImpl implements GenerateRefreshTokenUseCase {

    private final JwtService jwtService;

    private final SearchUserDetailsUseCase searchUserDetailsUseCase;

    @Override
    public TokenDetailPayload execute(final GenerateRefreshTokenPayload payload) {

        final var username = this.jwtService.extractUsername(payload.refreshToken());
        final var userDetails = this.searchUserDetailsUseCase.execute(username);

        if (this.jwtService.validateToken(payload.refreshToken(), userDetails)) {
            throw new RefreshTokenInvalidException("Refresh token is invalid or expired");
        }

        final var token = this.jwtService.generateToken(userDetails, JwtService.TokenType.ACCESS);
        final var refreshToken = this.jwtService.generateToken(userDetails, JwtService.TokenType.REFRESH);

        return TokenDetailPayload.builder()
            .token(token.token())
            .expiresIn(token.expiresAt())
            .refreshToken(refreshToken.token())
            .refreshTokenExpiresIn(refreshToken.expiresAt())
            .username(userDetails.getUsername())
            .build();
    }

}
