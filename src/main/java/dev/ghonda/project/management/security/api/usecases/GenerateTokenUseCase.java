package dev.ghonda.project.management.security.api.usecases;

import dev.ghonda.project.management.security.api.usecases.dto.LoginDetailPayload;
import dev.ghonda.project.management.security.api.usecases.dto.LoginPayload;

public interface GenerateTokenUseCase {

    LoginDetailPayload execute(LoginPayload payload);

}
