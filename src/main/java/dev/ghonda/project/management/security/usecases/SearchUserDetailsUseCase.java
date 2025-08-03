package dev.ghonda.project.management.security.usecases;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@FunctionalInterface
public interface SearchUserDetailsUseCase {

    UserDetails execute(final String username) throws UsernameNotFoundException;

}
