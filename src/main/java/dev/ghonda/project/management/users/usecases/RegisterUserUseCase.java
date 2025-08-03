package dev.ghonda.project.management.users.usecases;

import dev.ghonda.project.management.shared.dto.Resource;
import dev.ghonda.project.management.users.usecases.dto.RegisterUserPayload;

@FunctionalInterface
public interface RegisterUserUseCase {

    Resource execute(RegisterUserPayload payload);

}
