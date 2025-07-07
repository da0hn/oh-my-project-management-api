package dev.ghonda.project.management.users.usecases;

import dev.ghonda.project.management.users.rest.dto.UserDetailPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface SearchUsersUseCase {

    Page<UserDetailPayload> execute(
        String searchTerm,
        Pageable pageable
    );

}
