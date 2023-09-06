package com.festapp.festapp.controllers;

import com.festapp.festapp.dtos.AddDayDTO;
import com.festapp.festapp.entities.Day;
import com.festapp.festapp.services.ArtistService;
import com.festapp.festapp.services.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DayController {
    private DayService dayService;

    @Autowired
    public DayController(DayService dayService){
        this.dayService = dayService;
    }

    @PostMapping("/days")
    public ResponseEntity<?> addNewDay(@RequestBody AddDayDTO dayDTO){
        Day day = dayService.saveNewDay(dayDTO);
        return new ResponseEntity<>(day, HttpStatus.OK);
    }


}
