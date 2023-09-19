package com.festapp.festapp.services;

import com.festapp.festapp.dtos.AuthenticationRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
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
    public ValidationServiceImpl(Validator validator){
        this.validator = validator;
    }

    @Override
    public List<String> getMessageTemplate(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> errorMessageTemplates = new ArrayList<>();
        for (ConstraintViolation<?> violation : violations) {
            errorMessageTemplates.add(violation.getMessageTemplate());
        }
        return  errorMessageTemplates;
    }

    @Override
    public List<String> getValidationErrors(AuthenticationRequestDTO authenticationRequestDTO) {
        Set<ConstraintViolation<AuthenticationRequestDTO>> violations = validator.validate((AuthenticationRequestDTO) authenticationRequestDTO);
        List<String> validationErrors = new ArrayList<>();
        for (ConstraintViolation<AuthenticationRequestDTO> violation : violations) {
            validationErrors.add(violation.getMessage());
        }
        return validationErrors;
    }
}
