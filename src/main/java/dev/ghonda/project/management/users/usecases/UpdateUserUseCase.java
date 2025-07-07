package dev.ghonda.project.management.users.usecases;

import dev.ghonda.project.management.users.rest.dto.UpdateUserPayload;
import dev.ghonda.project.management.users.rest.dto.UserDetailPayload;

@FunctionalInterface
public interface UpdateUserUseCase {

    UserDetailPayload execute(Long userId, UpdateUserPayload payload);

}
