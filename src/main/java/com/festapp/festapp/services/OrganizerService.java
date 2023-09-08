package com.festapp.festapp.services;

import com.festapp.festapp.dtos.NewOrganizerDTO;
import com.festapp.festapp.dtos.NewOrganizerResponseDTO;
import com.festapp.festapp.entities.Organizer;

public interface OrganizerService {
    NewOrganizerResponseDTO saveNewOrganizer(NewOrganizerDTO organizerDTO);
}
