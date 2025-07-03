package dev.ghonda.project.management.shared.validators;

import dev.ghonda.project.management.shared.validators.annotations.UniqueBy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UniqueByValidator implements ConstraintValidator<UniqueBy, Object> {

    private final EntityManager entityManager;

    private String field;

    private String fieldId;

    @Override
    public void initialize(final UniqueBy constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldId = constraintAnnotation.fieldId();
    }

    @Override
    public boolean isValid(final Object object, final ConstraintValidatorContext context) {

        final var beanWrapper = new BeanWrapperImpl(object);
        final var fieldValue = beanWrapper.getPropertyValue(this.field);
        final var fieldIdValue = beanWrapper.getPropertyValue(this.fieldId);

        if (fieldValue == null) {
            log.warn("Field '{}' is null, skipping validation for object: {}", this.field, object);
            return true;
        }

        final var entityName = Hibernate.unproxy(object).getClass().getSimpleName();

        if (log.isDebugEnabled()) {
            log.debug("Validating uniqueness for field '{}' in entity '{}', value: '{}'", this.field, entityName, fieldValue);
        }

        final var queryBuilder = new StringBuilder();
        queryBuilder.append(String.format("select count(e) from %s e where e.%s = :value", entityName, this.field));

        if (fieldIdValue != null) {
            queryBuilder.append(String.format(" and e.%s != :id", this.fieldId));
        }

        final var query = this.entityManager.createQuery(queryBuilder.toString(), Long.class);
        query.setParameter("value", fieldValue);
        query.setFlushMode(FlushModeType.COMMIT);

        if (fieldIdValue != null) {
            query.setParameter("id", fieldIdValue);
        }

        final var isValid = query.getSingleResult() == 0;

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode(this.field)
                .addConstraintViolation();
        }

        return isValid;
    }

}
