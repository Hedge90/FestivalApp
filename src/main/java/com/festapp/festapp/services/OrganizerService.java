package com.festapp.festapp.services;

import com.festapp.festapp.dtos.NewOrganizerDTO;
import com.festapp.festapp.dtos.NewOrganizerResponseDTO;

public interface OrganizerService {
    NewOrganizerResponseDTO saveNewOrganizer(NewOrganizerDTO organizerDTO) throws Exception;
    String createJwtToken(String email);
}
