package dev.ghonda.project.management.users.rest;

import dev.ghonda.project.management.shared.dto.ApiResponse;
import dev.ghonda.project.management.shared.dto.Resource;
import dev.ghonda.project.management.users.rest.dto.RegisterUserPayload;
import dev.ghonda.project.management.users.rest.dto.UpdateUserPayload;
import dev.ghonda.project.management.users.ports.api.RegisterUserUseCase;
import dev.ghonda.project.management.users.ports.api.UpdateUserUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;

    private final UpdateUserUseCase updateUserUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<Resource>> registerUser(@RequestBody @Valid final RegisterUserPayload payload) {

        final var output = this.registerUserUseCase.execute(payload);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.of(output));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> updateUser(
        @PathVariable final Long userId,
        @RequestBody @Valid final UpdateUserPayload payload
    ) {
        this.updateUserUseCase.execute(userId, payload);
        return ResponseEntity.ok(ApiResponse.of(null));
    }

}
