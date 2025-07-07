package dev.ghonda.project.management.users.ports.api;

import dev.ghonda.project.management.users.rest.dto.UpdateUserPayload;

@FunctionalInterface
public interface UpdateUserUseCase {

    void execute(Long userId, UpdateUserPayload payload);

}
