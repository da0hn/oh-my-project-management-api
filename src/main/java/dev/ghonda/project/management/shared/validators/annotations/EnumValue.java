package dev.ghonda.project.management.shared.validators.annotations;

import dev.ghonda.project.management.shared.validators.EnumValueValidator;
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
@Constraint(validatedBy = EnumValueValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValue {

    Class<? extends Enum<?>> enumClass();

    String message() default "must be any of enum {enumClass}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
