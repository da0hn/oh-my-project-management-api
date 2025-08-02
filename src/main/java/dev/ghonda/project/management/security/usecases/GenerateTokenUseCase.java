package dev.ghonda.project.management.security.usecases;

import dev.ghonda.project.management.security.usecases.dto.LoginDetailPayload;
import dev.ghonda.project.management.security.usecases.dto.LoginPayload;

@FunctionalInterface
public interface GenerateTokenUseCase {

    LoginDetailPayload execute(LoginPayload payload);

}
