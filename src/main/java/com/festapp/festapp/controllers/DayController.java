package com.festapp.festapp.controllers;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.dtos.NewArtistDTO;
import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.services.ArtistService;
import com.festapp.festapp.services.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DayController {
    private DayService dayService;

    private ArtistService artistService;

    @Autowired
    public DayController(DayService dayService, ArtistService artistService) {
        this.dayService = dayService;
        this.artistService = artistService;
    }

    @PostMapping("/days")
    public ResponseEntity<?> addNewDay(@RequestBody NewDayDTO dayDTO) {
        return new ResponseEntity<>(dayService.saveNewDay(dayDTO), HttpStatus.OK);
    }

    @PostMapping("/add-artist")
    public ResponseEntity<ArtistDTO> addArtistToDay(@RequestBody NewArtistDTO newArtistDTO) {
        return new ResponseEntity<>(artistService.createNewArtist(newArtistDTO), HttpStatus.OK);
    }
}
