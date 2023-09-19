package com.festapp.festapp.services;

import com.festapp.festapp.dtos.AuthenticationRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ValidationServiceImplTest {

    private final ValidationServiceImpl validationService;

    @Autowired
    ValidationServiceImplTest(ValidationServiceImpl validationService) {
        this.validationService = validationService;
    }

    @Test
    public void getMessageTemplate_WhenViolationsExist_ReturnsTheAppropriateViolations() {
        ConstraintViolation<?> violation1 = createMockViolation("Violation 1");
        ConstraintViolation<?> violation2 = createMockViolation("Violation 2");

        Set<ConstraintViolation<?>> violations =Set.of(violation1, violation2);
        ConstraintViolationException exception = new ConstraintViolationException("Validation failed", violations);

        List<String> errorMessageTemplates = validationService.getMessageTemplate(exception);
        assertTrue(errorMessageTemplates.contains("Violation 1"));
        assertTrue(errorMessageTemplates.contains("Violation 2"));
        assertEquals(2, errorMessageTemplates.size());

    }

    @Test
    public void getValidationErrors_WhenValidAuthenticationRequestDTO_ReturnsEmptyList() {
        AuthenticationRequestDTO validRequest = new AuthenticationRequestDTO();
        validRequest.setEmail("leo@kristof.citrom");
        validRequest.setPassword("Egyketha1");

        List<String> validationErrors = validationService.getValidationErrors(validRequest);
        assertEquals(0, validationErrors.size());
    }

    @Test
    public void getValidationErrors_WhenInvalidAuthenticationRequestDTO_ReturnsValidationErrors() {
        AuthenticationRequestDTO invalidRequest = new AuthenticationRequestDTO();
        invalidRequest.setPassword("roszjelszo");

        List<String> validationErrors = validationService.getValidationErrors(invalidRequest);

        assertEquals(1, validationErrors.size());
        assertEquals("Email or password is invalid", validationErrors.get(0));
    }

    private ConstraintViolation<?> createMockViolation(String messageTemplate) {
        ConstraintViolation<?> violation = mock(ConstraintViolation.class);
        when(violation.getMessageTemplate()).thenReturn(messageTemplate);
        return violation;
    }



}
