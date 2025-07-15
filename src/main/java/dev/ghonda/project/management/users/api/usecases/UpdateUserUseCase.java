package dev.ghonda.project.management.users.api.usecases;

import dev.ghonda.project.management.users.internal.usecases.dto.UpdateUserPayload;
import dev.ghonda.project.management.users.internal.usecases.dto.UserDetailPayload;

@FunctionalInterface
public interface UpdateUserUseCase {

    UserDetailPayload execute(Long userId, UpdateUserPayload payload);

}
