package dev.ghonda.project.management.users.usecases.impl;

import dev.ghonda.project.management.shared.annotations.UseCase;
import dev.ghonda.project.management.users.domain.UserJpaRepository;
import dev.ghonda.project.management.users.usecases.DeleteUserUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserJpaRepository userJpaRepository;

    @Override
    @Transactional
    public void execute(final Long userId) {
        if (log.isInfoEnabled()) { log.info("Iniciando exclusão do usuário. userId: {}", userId); }
        if (log.isDebugEnabled()) log.debug("m=execute(userId: {})", userId);

        this.userJpaRepository.deleteById(userId);

        if (log.isInfoEnabled()) { log.info("Usuário excluído com sucesso. userId: {}", userId); }
    }

}
