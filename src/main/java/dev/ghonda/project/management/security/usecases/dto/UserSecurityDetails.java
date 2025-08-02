package dev.ghonda.project.management.security.usecases.dto;

import dev.ghonda.project.management.users.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

public class UserSecurityDetails implements UserDetails {

    @Serial
    private static final long serialVersionUID = 607337130906548109L;

    private final User user;

    public UserSecurityDetails(final User user) {
        this.user = Objects.requireNonNull(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(this.user.getRole())
            .map(role -> (GrantedAuthority) role::name)
            .toList();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

}
