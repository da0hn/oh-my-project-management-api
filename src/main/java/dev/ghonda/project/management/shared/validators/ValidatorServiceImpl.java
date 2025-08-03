package dev.ghonda.project.management.shared.validators;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class ValidatorServiceImpl implements ValidatorService {

    private final Validator validator;

    @Override
    public <T> void validate(final T object) {
        final Set<ConstraintViolation<T>> errors = this.validator.validate(object);
        if (!errors.isEmpty()) {
            throw new ConstraintViolationException(errors);
        }
    }

    @Override
    public <T> void validate(final T object, final Class<?>... groups) {
        final Set<ConstraintViolation<T>> errors = this.validator.validate(object, groups);
        if (!errors.isEmpty()) {
            throw new ConstraintViolationException(errors);
        }
    }

}
