package com.festapp.festapp.controllers;

import com.festapp.festapp.dtos.NewArtistDTO;
import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.exceptions.DayAlreadyExistsException;
import com.festapp.festapp.exceptions.NoSuchDayExistsException;
import com.festapp.festapp.services.ArtistService;
import com.festapp.festapp.services.DayService;
import com.festapp.festapp.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/days")
public class DayController {
    private DayService dayService;

    private ArtistService artistService;

    private ValidationService validationService;

    @Autowired
    public DayController(DayService dayService, ArtistService artistService, ValidationService validationService) {
        this.dayService = dayService;
        this.artistService = artistService;
        this.validationService = validationService;
    }

    @PostMapping
    public ResponseEntity<?> addNewDay(@RequestBody NewDayDTO dayDTO) {
        try {
            return new ResponseEntity<>(dayService.saveNewDay(dayDTO), HttpStatus.OK);
        } catch (DayAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add-artist")
    public ResponseEntity<?> addArtistToDay(@RequestBody NewArtistDTO newArtistDTO) {
        List<String> validationErrors = validationService.getValidationErrors(newArtistDTO);
        if (!validationErrors.isEmpty()) {
            return ResponseEntity.badRequest().body(validationErrors);
        }
        try {
            return new ResponseEntity<>(artistService.createNewArtist(newArtistDTO), HttpStatus.OK);
        } catch (NoSuchDayExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
