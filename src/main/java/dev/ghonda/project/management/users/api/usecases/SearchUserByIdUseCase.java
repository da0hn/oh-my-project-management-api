package dev.ghonda.project.management.users.api.usecases;

import dev.ghonda.project.management.users.api.usecases.dto.UserDetailPayload;

@FunctionalInterface
public interface SearchUserByIdUseCase {

    UserDetailPayload execute(Long userId);

}
