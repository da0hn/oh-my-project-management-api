package dev.ghonda.project.management.shared.validators;

import dev.ghonda.project.management.shared.validators.annotations.AbsentNotBlank;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class AbsentNotBlankValidator implements ConstraintValidator<AbsentNotBlank, String> {

    @Override
    public void initialize(final AbsentNotBlank constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (value == null) return true;
        return value.isBlank();
    }

}
