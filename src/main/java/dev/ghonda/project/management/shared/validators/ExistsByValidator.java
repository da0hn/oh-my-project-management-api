package dev.ghonda.project.management.shared.validators;

import dev.ghonda.project.management.shared.validators.annotations.ExistsBy;
import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExistsByValidator implements ConstraintValidator<ExistsBy, Object> {

    private final EntityManager entityManager;

    private String field;

    private Class<?> entity;

    @Override
    public void initialize(final ExistsBy constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.entity = constraintAnnotation.entity();
    }

    @Override
    public boolean isValid(final Object object, final ConstraintValidatorContext context) {
        final var query = """
                          select count(e)
                          from %s e
                          where e.%s = :value
                          """.formatted(this.entity.getSimpleName(), this.field);
        final var quantity = this.entityManager.createQuery(query, Long.class)
            .setParameter("value", object)
            .getSingleResult();
        return quantity == 0;
    }

}
