package com.festapp.festapp.controllers;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/artist")
public class ArtistController {

    private ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<?> getArtist(@PathVariable String name) {
        ArtistDTO artistDTO = artistService.getArtistByName(name);
        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }
}
