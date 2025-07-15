package dev.ghonda.project.management.users.internal.usecases;

import dev.ghonda.project.management.shared.annotations.UseCase;
import dev.ghonda.project.management.users.domain.UserRepository;
import dev.ghonda.project.management.users.internal.usecases.dto.UserDetailPayload;
import dev.ghonda.project.management.users.api.usecases.SearchUsersUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@UseCase
@AllArgsConstructor
public class SearchUsersUseCaseImpl implements SearchUsersUseCase {

    private final UserRepository userRepository;

    @Override
    public Page<UserDetailPayload> execute(
        final String searchTerm,
        final Pageable pageable
    ) {
        if (log.isInfoEnabled()) { log.info("Procurando usuários com o termo de busca: {}", searchTerm); }
        if (log.isDebugEnabled()) log.debug("m=execute(searchTerm: {}, pageable: {})", searchTerm, pageable);

        return this.userRepository.findAll(searchTerm, pageable)
            .map(UserDetailPayload::of);
    }

}
