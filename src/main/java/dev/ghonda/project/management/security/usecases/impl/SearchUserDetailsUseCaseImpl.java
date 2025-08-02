package dev.ghonda.project.management.security.usecases.impl;

import dev.ghonda.project.management.security.usecases.SearchUserDetailsUseCase;
import dev.ghonda.project.management.security.usecases.dto.UserSecurityDetails;
import dev.ghonda.project.management.users.api.UserPublicApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SearchUserDetailsUseCaseImpl implements SearchUserDetailsUseCase {

    private UserPublicApi userPublicAPI;

    public UserDetails execute(final String username) throws UsernameNotFoundException {
        if (log.isDebugEnabled()) { log.debug("m=loadUserByUsername(username={})", username); }

        return this.userPublicAPI.findUserByUsername(username)
            .map(UserSecurityDetails::new)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário %s não encontrado".formatted(username)));
    }

}
