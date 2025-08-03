package dev.ghonda.project.management.security.usecases;

import dev.ghonda.project.management.security.usecases.dto.GenerateRefreshTokenPayload;
import dev.ghonda.project.management.security.usecases.dto.TokenDetailPayload;

public interface GenerateRefreshTokenUseCase {

    TokenDetailPayload execute(GenerateRefreshTokenPayload payload);

}
