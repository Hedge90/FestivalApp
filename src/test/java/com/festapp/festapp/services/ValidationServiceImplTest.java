package com.festapp.festapp.services;

import com.festapp.festapp.dtos.NewOrganizerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ValidationServiceImplTest {

    private final ValidationServiceImpl validationService;

    @Autowired
    ValidationServiceImplTest(ValidationServiceImpl validationService) {
        this.validationService = validationService;
    }

    @Test
    public void getValidationErrors_WhenValidDTO_ReturnsEmptyList() {

        NewOrganizerDTO validDTO = new NewOrganizerDTO();
        validDTO.setName("Franz Ferdinand");
        validDTO.setEmail("archdude@kaiser.com");
        validDTO.setPassword("Monarchy48");

        List<String> validationErrors = validationService.getValidationErrors(validDTO);

        assertEquals(0, validationErrors.size());
    }

    @Test
    public void getValidationErrors_WhenInvalidDTO_ReturnsValidationErrors() {
        NewOrganizerDTO invalidDTO = new NewOrganizerDTO();

        invalidDTO.setName("");
        invalidDTO.setEmail("archdudekaiser.com");
        invalidDTO.setPassword("kossuth");
        List<String> validationErrors = validationService.getValidationErrors(invalidDTO);

        assertTrue(validationErrors.size() > 0);
        assertTrue(validationErrors.contains("Name must be included"));
        assertTrue(validationErrors.contains("Valid Email is required"));
        assertTrue(validationErrors.contains("Password must be at least 6 characters long and contain at least one capital letter and one non-alphabetical character"));
    }
}
