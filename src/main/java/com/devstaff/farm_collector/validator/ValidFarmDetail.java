package com.devstaff.farm_collector.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE_USE, METHOD, FIELD, ANNOTATION_TYPE})
@Documented
@Constraint(validatedBy = FarmDetailValidator.class)
public @interface ValidFarmDetail {
    String message() default "invalid object";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
