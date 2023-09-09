package com.festapp.festapp.controllers;

import com.festapp.festapp.dtos.NewOrganizerDTO;
import com.festapp.festapp.entities.Organizer;
import com.festapp.festapp.services.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class OrganizerController {
    private final OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService){
        this.organizerService = organizerService;
    }

    @PostMapping(path = "/organizer")
    public ResponseEntity<?> registerOrganizer(@RequestBody NewOrganizerDTO organizerDTO){
        return new ResponseEntity<>(organizerService.saveNewOrganizer(organizerDTO), HttpStatus.OK);
    }
}
