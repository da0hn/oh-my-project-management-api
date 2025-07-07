package dev.ghonda.project.management.users.rest.dto;

import dev.ghonda.project.management.users.domain.Role;
import dev.ghonda.project.management.users.domain.User;

public record UserDetailPayload(
    Long id,
    String username,
    String fullName,
    String email,
    Role role
) {

    public static UserDetailPayload of(final User user) {
        return new UserDetailPayload(
            user.getId(),
            user.getUsername(),
            user.getFullName(),
            user.getEmail(),
            user.getRole()
        );
    }

}
