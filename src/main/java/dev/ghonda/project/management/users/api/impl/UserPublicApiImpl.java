package dev.ghonda.project.management.users.api.impl;

import dev.ghonda.project.management.users.api.UserPublicApi;
import dev.ghonda.project.management.users.domain.User;
import dev.ghonda.project.management.users.domain.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserPublicApiImpl implements UserPublicApi {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserByUsername(final String username) {
        return this.userRepository.findUserByUsername(username);
    }

}
