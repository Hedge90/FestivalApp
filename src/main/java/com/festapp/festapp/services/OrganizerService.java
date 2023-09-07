package com.festapp.festapp.services;

import com.festapp.festapp.dtos.NewOrganizerDTO;
import com.festapp.festapp.entities.Organizer;

public interface OrganizerService {
    Organizer saveNewOrganizer(NewOrganizerDTO organizerDTO);
}
