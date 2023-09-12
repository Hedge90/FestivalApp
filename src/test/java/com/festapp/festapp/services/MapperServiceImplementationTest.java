package com.festapp.festapp.services;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.entities.Artist;
import com.festapp.festapp.entities.Day;
import com.festapp.festapp.enums.DayName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class MapperServiceImplementationTest {

    private MapperServiceImplementation mapperServiceImplementation;

    @Autowired
    MapperServiceImplementationTest(MapperServiceImplementation mapperServiceImplementation) {
        this.mapperServiceImplementation = mapperServiceImplementation;
    }

    @Test
    void convertArtistToArtistDTO_withProperArtist_returnsCorrectArtistDTO() {
        Artist artist = new Artist("Kozso");
        ArtistDTO artistDTO = mapperServiceImplementation.convertArtistToArtistDTO(artist);

        Assertions.assertEquals("Kozso", artistDTO.getName());
    }

    @Test
    void convertDayToNewDayDTO_withProperDay_returnsCorrectDayDTO() {
        Day day = new Day(LocalDate.of(2023,9,8),DayName.FRIDAY);
        NewDayDTO dayDTO = mapperServiceImplementation.convertDayToNewDayDTO(day);

        Assertions.assertEquals(LocalDate.of(2023,9,8),dayDTO.getDate());
        Assertions.assertEquals("FRIDAY", dayDTO.getName());
    }

    @Test
    void convertOrganizerToNewOrganizerResponseDTO_withProperOrganizer_returnsCorrectOrganizerDTO() {
        Day day = new Day(LocalDate.of(2023,9,8), DayName.FRIDAY);
        NewDayDTO dayDTO = mapperServiceImplementation.convertDayToNewDayDTO(day);

        Assertions.assertEquals(LocalDate.of(2023,9,8),dayDTO.getDate());
        Assertions.assertEquals("FRIDAY", dayDTO.getName());
    }


}