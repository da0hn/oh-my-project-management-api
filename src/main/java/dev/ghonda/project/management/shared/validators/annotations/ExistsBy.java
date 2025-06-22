package dev.ghonda.project.management.shared.validators.annotations;

import dev.ghonda.project.management.shared.validators.ExistsByValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ExistsByValidator.class)
@Target({ ElementType.TYPE, ElementType.RECORD_COMPONENT, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsBy {

    String field();

    Class<?> entity();

    String message() default "{value.not-exists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
