package dev.ghonda.project.management.users.usecases;

import dev.ghonda.project.management.users.usecases.dto.UpdateUserPayload;
import dev.ghonda.project.management.users.usecases.dto.UserDetailPayload;

@FunctionalInterface
public interface UpdateUserUseCase {

    UserDetailPayload execute(Long userId, UpdateUserPayload payload);

}
