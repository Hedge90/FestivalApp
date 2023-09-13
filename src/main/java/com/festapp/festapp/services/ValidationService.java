package com.festapp.festapp.services;

import com.festapp.festapp.dtos.AuthenticationRequestDTO;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface ValidationService {
    List<String> getMessageTemplate(ConstraintViolationException e);
    List<String> getValidationErrors(AuthenticationRequestDTO authenticationRequestDTO);
}
