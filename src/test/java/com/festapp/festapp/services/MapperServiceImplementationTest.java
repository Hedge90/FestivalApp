package com.festapp.festapp.services;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.entities.Artist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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
}