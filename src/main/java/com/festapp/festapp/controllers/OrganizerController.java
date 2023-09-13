package com.festapp.festapp.controllers;

import com.festapp.festapp.dtos.AuthenticationRequestDTO;

import com.festapp.festapp.dtos.AuthenticationResponseDTO;
import com.festapp.festapp.dtos.NewOrganizerDTO;
import com.festapp.festapp.services.OrganizerService;
import com.festapp.festapp.services.ValidationService;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(path = "/api")
@Validated
public class OrganizerController {
    private final OrganizerService organizerService;
    private final ValidationService validationService;

    @Autowired
    public OrganizerController(OrganizerService organizerService, ValidationService validationService) {
        this.organizerService = organizerService;
        this.validationService = validationService;
    }

    @PostMapping("/organizer")
    public ResponseEntity<?> registerOrganizer(@RequestBody NewOrganizerDTO organizerDTO) {
        try {
            return new ResponseEntity<>(organizerService.saveNewOrganizer(organizerDTO), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().body(validationService.getMessageTemplate(e));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Email already exists!");
        }
    }

    @PostMapping(path = "/organizer/login")
    public ResponseEntity<?> loginOrganizer(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        List<String> validationErrors = validationService.getValidationErrors(authenticationRequestDTO);
        if (!validationErrors.isEmpty()) {
            return ResponseEntity.badRequest().body(validationErrors);
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponseDTO("ok", organizerService.createJwtToken(authenticationRequestDTO.getEmail())));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body("Email or Password is invalid!");
        }
    }
}
