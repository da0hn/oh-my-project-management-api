package dev.ghonda.project.management.security.configuration.filters;

import dev.ghonda.project.management.security.configuration.constants.JwtConstants;
import dev.ghonda.project.management.security.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final JwtService jwtService;

    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            final var authenticationHeader = request.getHeader(JwtConstants.AUTHENTICATION_HEADER);
            if (authenticationHeader == null || !authenticationHeader.startsWith(JwtConstants.BEARER)) {
                filterChain.doFilter(request, response);
                return;
            }
            final var jwt = authenticationHeader.substring(JwtConstants.BEARER.length());
            final var username = this.jwtService.extractUsername(jwt);
            final var userDetails = this.userDetailsService.loadUserByUsername(username);
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                if (this.jwtService.validateToken(jwt, userDetails)) {
                    final var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                    );
                    authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                            .buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        }
        catch (final IOException | ServletException e) {
            this.logger.error("Não foi possível identificar o usuário na requisição", e);
            this.handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }

}
