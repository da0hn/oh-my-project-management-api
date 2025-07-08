package dev.ghonda.project.management.users.ports.api.usecases;

import dev.ghonda.project.management.shared.dto.Resource;
import dev.ghonda.project.management.users.rest.dto.RegisterUserPayload;

@FunctionalInterface
public interface RegisterUserUseCase {

    Resource execute(RegisterUserPayload payload);

}
