package dev.ghonda.project.management.users.usecases;

import dev.ghonda.project.management.users.usecases.dto.UserDetailPayload;

@FunctionalInterface
public interface SearchUserByIdUseCase {

    UserDetailPayload execute(Long userId);

}
