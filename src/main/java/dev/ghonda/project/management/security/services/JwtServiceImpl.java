package dev.ghonda.project.management.security.services;

import dev.ghonda.project.management.security.configuration.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Slf4j
@Component
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtProperties jwtProperties;

    @Override
    public String extractUsername(final String token) {
        return this.extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean validateToken(final String token, final UserDetails userDetails) {
        final String username = this.extractUsername(token);
        Jwts.parserBuilder()
            .setSigningKey(this.getSignKey())
            .build()
            .parseClaimsJws(token);
        return (username.equals(userDetails.getUsername()) && !this.isTokenExpired(token));
    }

    @Override
    public GeneratedToken generateToken(final UserDetails userDetails, final TokenType type) {
        final var authorities = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .toList();

        final var expiresAt = this.getExpiration(type);
        final var newToken = Jwts.builder()
            .claim("authorities", authorities)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setIssuer("project-management-api")
            .setExpiration(expiresAt)
            .setId(UUID.randomUUID().toString())
            .signWith(this.getSignKey(), SignatureAlgorithm.HS256)
            .compact();

        return GeneratedToken.builder()
            .token(newToken)
            .expiresAt(
                expiresAt.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime()
            )
            .type(type)
            .build();
    }

    private <T> T extractClaim(final String token, final Function<? super Claims, T> claimsResolver) {
        final var claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Boolean isTokenExpired(final String token) {
        return this.extractExpiration(token).before(new Date());
    }

    private Date getExpiration(final TokenType type) {
        return switch (type) {
            case ACCESS -> new Date(System.currentTimeMillis() + this.jwtProperties.expiration().toMillis());
            case REFRESH -> new Date(System.currentTimeMillis() + this.jwtProperties.refreshExpiration().toMillis());
        };
    }

    private Date extractExpiration(final String token) {
        return this.extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(final String token) {
        return Jwts.parserBuilder()
            .setSigningKey(this.getSignKey())
            .build()
            .parseClaimsJws(token)
            .getBody();

    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.jwtProperties.secret()));
    }

}
