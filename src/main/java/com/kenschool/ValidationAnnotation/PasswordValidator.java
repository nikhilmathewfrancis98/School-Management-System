package com.kenschool.ValidationAnnotation;

import com.kenschool.CustomValidation.PasswordStrengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordStrengthValidator.class)
public @interface PasswordValidator {
    String message() default "Password Should Contain atleast 8 charactes,1 capital letter, 1 Special symbol and numbers";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

//    String regexp() default ".*"; // This is not working properly please check
    // I tried to added the regex mather code inside the PasswordStrengthValidator class
    // Check that class or we can add this field name instead

}
