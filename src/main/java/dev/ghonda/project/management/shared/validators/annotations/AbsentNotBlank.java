package dev.ghonda.project.management.shared.validators.annotations;

import dev.ghonda.project.management.shared.validators.AbsentNotBlankValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({
    ElementType.TYPE,
    ElementType.METHOD,
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.CONSTRUCTOR,
    ElementType.RECORD_COMPONENT
})
@Constraint(validatedBy = AbsentNotBlankValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface AbsentNotBlank {

    String message() default "must be null or not blank";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
