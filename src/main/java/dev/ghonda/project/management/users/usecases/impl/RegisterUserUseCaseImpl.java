package dev.ghonda.project.management.users.usecases.impl;

import dev.ghonda.project.management.shared.annotations.UseCase;
import dev.ghonda.project.management.shared.dto.Resource;
import dev.ghonda.project.management.shared.dto.SimpleResource;
import dev.ghonda.project.management.shared.validators.ValidatorService;
import dev.ghonda.project.management.users.domain.Role;
import dev.ghonda.project.management.users.domain.User;
import dev.ghonda.project.management.users.domain.UserRepository;
import dev.ghonda.project.management.users.usecases.RegisterUserUseCase;
import dev.ghonda.project.management.users.usecases.dto.RegisterUserPayload;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@AllArgsConstructor
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private ValidatorService validatorService;

    @Override
    @Transactional
    public Resource execute(final RegisterUserPayload payload) {
        if (log.isInfoEnabled()) { log.info("Registrando novo usuário {}", payload.username()); }
        if (log.isDebugEnabled()) {
            log.debug("m=execute(payload: {})", payload);
        }

        final var newUser = User.builder()
            .id(null)
            .username(payload.username())
            .email(payload.email())
            .password(this.passwordEncoder.encode(payload.password()))
            .fullName(payload.fullName())
            .role(Role.valueOf(payload.role()))
            .build();

        this.validatorService.validate(newUser);

        this.userRepository.save(newUser);

        return SimpleResource.of(newUser.getId());
    }

}
