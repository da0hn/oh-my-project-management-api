package dev.ghonda.project.management.users.api;

import dev.ghonda.project.management.users.domain.User;

import java.util.Optional;

@FunctionalInterface
public interface UserPublicApi {

    Optional<User> findUserByUsername(String username);

}
