package dev.ghonda.project.management.users.ports.api.usecases;

import dev.ghonda.project.management.users.rest.dto.UserDetailPayload;

@FunctionalInterface
public interface SearchUserByIdUseCase {

    UserDetailPayload execute(Long userId);

}
