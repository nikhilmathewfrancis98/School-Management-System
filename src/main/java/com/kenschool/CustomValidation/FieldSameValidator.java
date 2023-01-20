package com.kenschool.CustomValidation;

import com.kenschool.ValidationAnnotation.FiledValueMatch;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldSameValidator implements ConstraintValidator<FiledValueMatch, Object> {
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FiledValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);
//        if (fieldValue != null&&fieldValue.toString().startsWith("")) {
//            return fieldValue.equals(fieldMatchValue);
//        } else {
//            return fieldMatchValue == null;
//        }
        if (fieldValue != null) {
            if (fieldValue.toString().startsWith("$2a")) {
                return true;
            } else {
                return fieldValue.equals(fieldMatchValue);
            }
        } else {
            return fieldMatchValue == null;
        }
    }
}
