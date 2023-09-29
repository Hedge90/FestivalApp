package com.festapp.festapp.controllers;


import com.festapp.festapp.dtos.NewArtistDTO;
import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.exceptions.DayAlreadyExistsException;
import com.festapp.festapp.services.ArtistService;
import com.festapp.festapp.services.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/days")
public class DayController {
    private DayService dayService;

    private ArtistService artistService;

    @Autowired
    public DayController(DayService dayService, ArtistService artistService) {
        this.dayService = dayService;
        this.artistService = artistService;
    }

    @PostMapping
    public ResponseEntity<?> addNewDay(@RequestBody NewDayDTO dayDTO) {
        try {
            return new ResponseEntity<>(dayService.saveNewDay(dayDTO), HttpStatus.OK);
        }catch (DayAlreadyExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add-artist")
    public ResponseEntity<?> addArtistToDay(@RequestBody NewArtistDTO newArtistDTO) {
        return new ResponseEntity<>(artistService.createNewArtist(newArtistDTO), HttpStatus.OK);
    }
}
