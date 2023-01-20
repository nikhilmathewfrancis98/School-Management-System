package com.kenschool.CustomValidation;

import com.kenschool.ValidationAnnotation.PasswordValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {
    private List<String> default_psswds;

    @Override
    public void initialize(PasswordValidator passwordValidator) {
        default_psswds = Arrays.asList("12345", "password", "qwerty", "guest", "123456789", "D1lakiss");
    }

    @Override
    public boolean isValid(String PasswordFiled, ConstraintValidatorContext cxt) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);


        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(PasswordFiled);

        // Return if the password
        // matched the ReGex

        return m.matches() && PasswordFiled != null && (!default_psswds.contains(PasswordFiled));
    }
}
