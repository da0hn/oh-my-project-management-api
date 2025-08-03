package dev.ghonda.project.management.users.usecases.dto;

import dev.ghonda.project.management.shared.validators.annotations.EnumValue;
import dev.ghonda.project.management.users.domain.Role;

public record UpdateUserPayload(
    String username,
    String fullName,
    String email,
    @EnumValue(enumClass = Role.class)
    String role
) {
}
