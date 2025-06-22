package dev.ghonda.project.management.shared.validators;

import dev.ghonda.project.management.shared.validators.annotations.FieldsValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

@Slf4j
public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    private String field;

    private String fieldMatch;

    @Override
    public void initialize(final FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(final Object object, final ConstraintValidatorContext context) {
        try {
            final var method = object.getClass().getMethod(this.field);
            final var methodMatch = object.getClass().getMethod(this.fieldMatch);
            final var fieldValue = method.invoke(object);
            final var fieldValueMatch = methodMatch.invoke(object);

            if (fieldValue == null && fieldValueMatch == null) {
                return true;
            }

            if (fieldValue != null && fieldValue.equals(fieldValueMatch)) {
                return true;
            }
        }
        catch (final Exception e) {
            log.warn("Ocorreu um erro ao validar durante a validação das senhas. Erro: {}", e.getMessage(), e);
            return false;
        }
        context.disableDefaultConstraintViolation();
        final HibernateConstraintValidatorContext hibernateContext = context.unwrap(HibernateConstraintValidatorContext.class);

        hibernateContext.buildConstraintViolationWithTemplate(hibernateContext.getDefaultConstraintMessageTemplate())
            .addPropertyNode(this.field)
            .addConstraintViolation();

        hibernateContext.buildConstraintViolationWithTemplate(hibernateContext.getDefaultConstraintMessageTemplate())
            .addPropertyNode(this.fieldMatch)
            .addConstraintViolation();
        return false;
    }

}
