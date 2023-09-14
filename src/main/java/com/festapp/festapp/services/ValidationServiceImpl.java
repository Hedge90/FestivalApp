package com.festapp.festapp.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ValidationServiceImpl implements ValidationService {
    Validator validator;

    @Autowired
    public ValidationServiceImpl(Validator validator) {
        this.validator = validator;
    }

    public List<String> getValidationErrors(Object dto) {
        Set<ConstraintViolation<Object>> violations = validator.validate(dto);
        List<String> validationErrors = new ArrayList<>();
        for (ConstraintViolation<Object> violation : violations) {
            validationErrors.add(violation.getMessage());
        }
        return validationErrors;
    }
}
