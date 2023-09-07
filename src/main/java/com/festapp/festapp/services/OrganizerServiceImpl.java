package com.festapp.festapp.services;

import com.festapp.festapp.dtos.NewOrganizerDTO;
import com.festapp.festapp.entities.Organizer;
import com.festapp.festapp.repositories.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizerServiceImpl implements OrganizerService {
    private final OrganizerRepository organizerRepository;

    @Autowired
    public OrganizerServiceImpl(OrganizerRepository organizerRepository){
        this.organizerRepository = organizerRepository;
    }

    @Override
    public Organizer saveNewOrganizer(NewOrganizerDTO organizerDTO) {
        Organizer organizer = new Organizer(organizerDTO.getName(),organizerDTO.getEmail(),organizerDTO.getPassword());
        return organizerRepository.save(organizer);
    }
}
