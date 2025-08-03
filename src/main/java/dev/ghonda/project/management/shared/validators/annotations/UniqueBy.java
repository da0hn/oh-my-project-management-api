package dev.ghonda.project.management.shared.validators.annotations;

import dev.ghonda.project.management.shared.validators.UniqueByValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(UniqueBy.Fields.class)
@Constraint(validatedBy = UniqueByValidator.class)
public @interface UniqueBy {

    String field();

    String fieldId() default "id";

    String message() default "Field value must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Documented
    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface Fields {
        UniqueBy[] value();
    }

}
