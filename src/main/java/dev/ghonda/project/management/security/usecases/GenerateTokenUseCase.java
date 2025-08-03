package dev.ghonda.project.management.security.usecases;

import dev.ghonda.project.management.security.usecases.dto.TokenDetailPayload;
import dev.ghonda.project.management.security.usecases.dto.GenerateTokenPayload;

@FunctionalInterface
public interface GenerateTokenUseCase {

    TokenDetailPayload execute(GenerateTokenPayload payload);

}
