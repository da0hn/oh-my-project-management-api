package dev.ghonda.project.management.users.rest;

import dev.ghonda.project.management.shared.dto.ApiCollectionPageResponse;
import dev.ghonda.project.management.shared.dto.ApiResponse;
import dev.ghonda.project.management.shared.dto.Resource;
import dev.ghonda.project.management.users.rest.dto.RegisterUserPayload;
import dev.ghonda.project.management.users.rest.dto.UpdateUserPayload;
import dev.ghonda.project.management.users.rest.dto.UserDetailPayload;
import dev.ghonda.project.management.users.ports.api.usecases.DeleteUserUseCase;
import dev.ghonda.project.management.users.ports.api.usecases.RegisterUserUseCase;
import dev.ghonda.project.management.users.ports.api.usecases.SearchUserByIdUseCase;
import dev.ghonda.project.management.users.ports.api.usecases.SearchUsersUseCase;
import dev.ghonda.project.management.users.ports.api.usecases.UpdateUserUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;

    private final UpdateUserUseCase updateUserUseCase;

    private final SearchUserByIdUseCase searchUserByIdUseCase;

    private final SearchUsersUseCase searchUsersUseCase;

    private final DeleteUserUseCase deleteUserUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<Resource>> registerUser(@RequestBody @Valid final RegisterUserPayload payload) {

        final var output = this.registerUserUseCase.execute(payload);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.of(output));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDetailPayload>> updateUser(
        @PathVariable final Long userId,
        @RequestBody @Valid final UpdateUserPayload payload
    ) {
        final var output = this.updateUserUseCase.execute(userId, payload);
        return ResponseEntity.ok(ApiResponse.of(output));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDetailPayload>> searchUserById(@PathVariable final Long userId) {
        final var output = this.searchUserByIdUseCase.execute(userId);
        return ResponseEntity.ok(ApiResponse.of(output));
    }

    @GetMapping
    public ResponseEntity<ApiCollectionPageResponse<UserDetailPayload>> searchAllUsers(
        @RequestParam(value = "search-term", required = false) final String searchTerm,
        @PageableDefault(size = 15, sort = { "username", "email", "id" }, direction = Sort.Direction.ASC) final Pageable pageable
    ) {
        final var output = this.searchUsersUseCase.execute(searchTerm, pageable);
        if (output.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiCollectionPageResponse.empty());
        }
        return ResponseEntity.ok(ApiCollectionPageResponse.of(output));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable final Long userId) {
        this.deleteUserUseCase.execute(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(ApiResponse.of(null));
    }

}
