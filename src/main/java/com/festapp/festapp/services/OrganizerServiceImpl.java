package com.festapp.festapp.services;

import com.festapp.festapp.dtos.NewOrganizerDTO;
import com.festapp.festapp.dtos.NewOrganizerResponseDTO;
import com.festapp.festapp.entities.Organizer;
import com.festapp.festapp.repositories.OrganizerRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizerServiceImpl implements OrganizerService {
    private final OrganizerRepository organizerRepository;
    private MapperService mapperService;

    @Autowired
    public OrganizerServiceImpl(OrganizerRepository organizerRepository, MapperService mapperService){
        this.organizerRepository = organizerRepository;
        this.mapperService = mapperService;
    }

    @Override
    public NewOrganizerResponseDTO saveNewOrganizer(NewOrganizerDTO organizerDTO) {
        return mapperService.convertOrganizerToNewOrganizerResponseDTO(organizerRepository.save(new Organizer(organizerDTO.getName(), organizerDTO.getEmail(), organizerDTO.getPassword())));
    }
}
