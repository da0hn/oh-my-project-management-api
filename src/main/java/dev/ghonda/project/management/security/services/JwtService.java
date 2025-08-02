package dev.ghonda.project.management.security.services;

import lombok.Builder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

public interface JwtService {

    String extractUsername(String token);

    boolean validateToken(String token, UserDetails userDetails);

    GeneratedToken generateToken(UserDetails userDetails, TokenType type);

    enum TokenType {
        ACCESS,
        REFRESH
    }

    @Builder
    record GeneratedToken(
        String token,
        LocalDateTime expiresAt,
        TokenType type
    ) {}

}
