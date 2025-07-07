package dev.ghonda.project.management.users.usecases.impl;

import dev.ghonda.project.management.shared.annotations.UseCase;
import dev.ghonda.project.management.users.domain.UserJpaRepository;
import dev.ghonda.project.management.users.rest.dto.UserDetailPayload;
import dev.ghonda.project.management.users.usecases.SearchUsersUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@UseCase
@AllArgsConstructor
public class SearchUsersUseCaseImpl implements SearchUsersUseCase {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Page<UserDetailPayload> execute(
        final String searchTerm,
        final Pageable pageable
    ) {
        if (log.isInfoEnabled()) { log.info("Procurando usuários com o termo de busca: {}", searchTerm); }
        if (log.isDebugEnabled()) log.debug("m=execute(searchTerm: {}, pageable: {})", searchTerm, pageable);

        return this.userJpaRepository.findAll(searchTerm, pageable)
            .map(UserDetailPayload::of);
    }

}
