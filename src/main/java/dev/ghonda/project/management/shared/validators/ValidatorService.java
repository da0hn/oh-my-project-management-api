package dev.ghonda.project.management.shared.validators;

public interface ValidatorService {

    <T> void validate(T object);

    <T> void validate(T object, Class<?>... groups);

}
