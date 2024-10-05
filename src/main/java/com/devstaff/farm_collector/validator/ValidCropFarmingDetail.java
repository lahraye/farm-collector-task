package com.devstaff.farm_collector.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE_USE, METHOD, FIELD, ANNOTATION_TYPE})
@Documented
@Constraint(validatedBy = CropFarmingDetailValidator.class)
public @interface ValidCropFarmingDetail {
    String message() default "invalid object";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
