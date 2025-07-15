package dev.ghonda.project.management.security.api;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "security.jwt")
public record JwtProperties(
    String secret,
    Duration expiration,
    String refreshSecret,
    Duration refreshExpiration
) {
}
