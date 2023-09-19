package com.festapp.festapp.controllers;

import com.festapp.festapp.dtos.AuthenticationRequestDTO;

import com.festapp.festapp.dtos.AuthenticationResponseDTO;
import com.festapp.festapp.dtos.ErrorDTO;
import com.festapp.festapp.dtos.NewOrganizerDTO;
import com.festapp.festapp.exceptions.EmailAlreadyExistsException;
import com.festapp.festapp.exceptions.InvalidPasswordException;
import com.festapp.festapp.services.OrganizerService;
import com.festapp.festapp.services.ValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/api/organizers")
public class OrganizerController {
    private final OrganizerService organizerService;
    private final ValidationService validationService;

    @Autowired
    public OrganizerController(OrganizerService organizerService, ValidationService validationService) {
        this.organizerService = organizerService;
        this.validationService = validationService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerOrganizer(@RequestBody NewOrganizerDTO organizerDTO) {
        List<String> validationErrors = validationService.getValidationErrors(organizerDTO);
        if (!validationErrors.isEmpty()) {
            return ResponseEntity.badRequest().body(validationErrors);
        }
        try {
            return new ResponseEntity<>(organizerService.saveNewOrganizer(organizerDTO), HttpStatus.OK);
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginOrganizer(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        List<String> validationErrors = validationService.getValidationErrors(authenticationRequestDTO);
        if (!validationErrors.isEmpty()) {
            return ResponseEntity.badRequest().body(validationErrors.get(0));
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponseDTO("ok", organizerService.createJwtToken(authenticationRequestDTO)));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO("Bad Request","Email or password is invalid"));
        } catch (InvalidPasswordException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
