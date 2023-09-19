package com.festapp.festapp.services;

import com.festapp.festapp.dtos.AuthenticationRequestDTO;
import com.festapp.festapp.dtos.NewOrganizerDTO;
import com.festapp.festapp.dtos.NewOrganizerResponseDTO;
import jakarta.validation.ConstraintViolationException;

public interface OrganizerService {

    NewOrganizerResponseDTO saveNewOrganizer(NewOrganizerDTO organizerDTO) throws ConstraintViolationException;

    String createJwtToken(AuthenticationRequestDTO authenticationRequestDTO);

}
