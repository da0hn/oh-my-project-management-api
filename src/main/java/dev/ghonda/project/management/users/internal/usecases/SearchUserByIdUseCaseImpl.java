package dev.ghonda.project.management.users.internal.usecases;

import dev.ghonda.project.management.shared.annotations.UseCase;
import dev.ghonda.project.management.shared.exceptions.ResourceNotFoundException;
import dev.ghonda.project.management.users.domain.UserRepository;
import dev.ghonda.project.management.users.api.usecases.dto.UserDetailPayload;
import dev.ghonda.project.management.users.api.usecases.SearchUserByIdUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@AllArgsConstructor
public class SearchUserByIdUseCaseImpl implements SearchUserByIdUseCase {

    private final UserRepository userRepository;

    @Override
    public UserDetailPayload execute(final Long userId) {
        if (log.isInfoEnabled()) log.info("Buscando usuário pelo id: {}", userId);
        if (log.isDebugEnabled()) log.debug("m=execute(userId: {})", userId);

        return this.userRepository.findById(userId)
            .map(UserDetailPayload::of)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

}
