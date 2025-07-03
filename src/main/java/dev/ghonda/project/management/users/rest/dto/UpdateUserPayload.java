package dev.ghonda.project.management.users.rest.dto;

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
