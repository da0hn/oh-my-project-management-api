package dev.ghonda.project.management.users.usecases;

import dev.ghonda.project.management.shared.annotations.UseCase;
import dev.ghonda.project.management.shared.exceptions.ResourceNotFoundException;
import dev.ghonda.project.management.shared.validators.ValidatorService;
import dev.ghonda.project.management.users.domain.Role;
import dev.ghonda.project.management.users.domain.UserJpaRepository;
import dev.ghonda.project.management.users.ports.api.UpdateUserUseCase;
import dev.ghonda.project.management.users.rest.dto.UpdateUserPayload;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@UseCase
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserJpaRepository userJpaRepository;

    private final ValidatorService validatorService;

    @Override
    @Transactional
    public void execute(final Long userId, final UpdateUserPayload payload) {
        if (log.isInfoEnabled()) { log.info("Iniciando atualização dos dados do usuário. userId: {}", userId); }
        if (log.isDebugEnabled()) {
            log.debug("m=execute(userId: {}, payload: {})", userId, payload);
        }

        final var user = this.userJpaRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Optional.ofNullable(payload.username())
            .ifPresent(user::changeUsername);
        Optional.ofNullable(payload.email())
            .ifPresent(user::changeEmail);
        Optional.ofNullable(payload.fullName())
            .ifPresent(user::changeFullName);
        Optional.ofNullable(payload.role())
            .map(Role::valueOf)
            .ifPresent(user::changeRole);

        this.validatorService.validate(user);

        this.userJpaRepository.save(user);

        if (log.isInfoEnabled()) { log.info("Dados do usuário atualizados com sucesso. userId: {}", userId); }
    }

}
