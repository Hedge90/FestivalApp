package com.festapp.festapp.controllers;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/artists")
public class ArtistController {

    private ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<ArtistDTO> getArtist(@PathVariable String name) {
        return new ResponseEntity<>(artistService.getArtistByName(name), HttpStatus.OK);
    }
}
