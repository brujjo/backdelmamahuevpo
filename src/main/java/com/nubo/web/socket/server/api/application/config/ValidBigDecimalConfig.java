package com.nubo.web.socket.server.api.application.config;

import com.nubo.web.socket.server.api.application.annotations.ValidBigDecimal;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class ValidBigDecimalConfig implements ConstraintValidator<ValidBigDecimal, BigDecimal> {
    private int precision;
    private int scale;
    private String message;

    @Override
    public void initialize(ValidBigDecimal constraintAnnotation) {
        this.precision = constraintAnnotation.precision();
        this.scale = constraintAnnotation.scale();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        int integerPartLength = value.precision() - value.scale();
        boolean isValid = integerPartLength + scale <= precision && value.scale() <= scale;
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    message.isEmpty() ?
                            String.format("The value must have a maximum precision of %d, a maximum scale of %d " +
                                            "and round to an absolute value less than 10^%d.",
                                    precision, scale, precision - scale) : message
            ).addConstraintViolation();
        }
        return isValid;
    }
}