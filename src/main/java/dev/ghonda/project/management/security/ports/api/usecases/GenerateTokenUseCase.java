package dev.ghonda.project.management.security.ports.api.usecases;

import dev.ghonda.project.management.security.rest.controller.dto.LoginDetailPayload;
import dev.ghonda.project.management.security.rest.controller.dto.LoginPayload;

public interface GenerateTokenUseCase {

    LoginDetailPayload execute(LoginPayload payload);

}
