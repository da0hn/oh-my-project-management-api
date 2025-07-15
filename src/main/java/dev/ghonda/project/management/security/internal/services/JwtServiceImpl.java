package dev.ghonda.project.management.security.internal.services;

import dev.ghonda.project.management.security.api.services.JwtService;
import dev.ghonda.project.management.security.api.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
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
        try {
            final String username = this.extractUsername(token);
            Jwts.parserBuilder()
                .setSigningKey(this.getSignKey())
                .build()
                .parseClaimsJws(token);
            return (username.equals(userDetails.getUsername()) && !this.isTokenExpired(token));
        }
        catch (final SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT signature or format", e);
            return false;
        }
        catch (final ExpiredJwtException e) {
            log.error("Token expired", e);
            return false;
        }
        catch (final UnsupportedJwtException e) {
            log.error("Unsupported JWT token", e);
            return false;
        }
        catch (final IllegalArgumentException e) {
            log.error("JWT claims string is empty", e);
            return false;
        }
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
