package dev.ghonda.project.management.users.usecases.dto;

import dev.ghonda.project.management.shared.validators.annotations.EnumValue;
import dev.ghonda.project.management.shared.validators.annotations.FieldsValueMatch;
import dev.ghonda.project.management.users.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@FieldsValueMatch(
    field = "password",
    fieldMatch = "confirmPassword",
    message = "Os valores dos campos {field} e {fieldMatch} não correspondem")
public record RegisterUserPayload(
    @NotBlank
    String username,
    @NotBlank
    String password,
    @NotBlank
    String confirmPassword,
    @NotBlank
    String fullName,
    @Email
    @NotNull
    String email,
    @NotNull
    @EnumValue(enumClass = Role.class)
    String role
) implements Serializable { }
