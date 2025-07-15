package dev.ghonda.project.management.users.api.usecases;

import dev.ghonda.project.management.shared.dto.Resource;
import dev.ghonda.project.management.users.internal.usecases.dto.RegisterUserPayload;

@FunctionalInterface
public interface RegisterUserUseCase {

    Resource execute(RegisterUserPayload payload);

}
