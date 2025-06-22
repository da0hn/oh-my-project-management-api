package dev.ghonda.project.management.shared.validators;

import dev.ghonda.project.management.shared.validators.annotations.EnumValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {

    private List<String> acceptedValues;

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(final EnumValue constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
        this.acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
            .map(Enum::name)
            .toList();
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        final var isValid = this.acceptedValues.contains(value);
        if (isValid) {
            return true;
        }
        final var messageTemplate = String.format(
            "O valor informado não é válido para %s, precisa ser qualquer um dos valores [ %s ]",
            this.enumClass.getSimpleName(),
            String.join(", ", this.acceptedValues)
        );
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageTemplate)
            .addConstraintViolation();
        return false;
    }

}
