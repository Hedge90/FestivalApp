package com.festapp.festapp.services;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.entities.Artist;
import com.festapp.festapp.exceptions.ArtistNotFoundException;
import com.festapp.festapp.repositories.ArtistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;


@SpringBootTest
class ArtistServiceImplementationTest {
    ArtistServiceImplementation artistService;

    ArtistRepository artistRepository;

    MapperService mapperService;

    @Autowired
    ArtistServiceImplementationTest() {
        artistRepository = Mockito.mock(ArtistRepository.class);
        mapperService = Mockito.mock(MapperService.class);
        this.artistService = new ArtistServiceImplementation(artistRepository, mapperService);
    }

    @Test
    void getArtistByName_withInputMatchingExistingArtist_returnsCorrectArtistDTO() {
        LocalDateTime date = LocalDateTime.now();
        Artist fakeArtist = new Artist("Kozso");
        fakeArtist.setDate(date);
        ArtistDTO artistDTO = new ArtistDTO("Kozso", date);
        Mockito.when(artistRepository.findArtistByName("Kozso")).thenReturn(Optional.of(fakeArtist));
        Mockito.when(mapperService.convertArtistToArtistDTO(fakeArtist)).thenReturn(artistDTO);
        ArtistDTO storedArtist = artistService.getArtistByName("Kozso");

        Assertions.assertEquals("Kozso", storedArtist.getName());
        Assertions.assertEquals(date, storedArtist.getDate());
    }

    @Test
    void getArtistByName_withInputNotMatchingExistingArtist_throwsArtistNotFoundException() {
        Mockito.when(artistRepository.findArtistByName("Cujo")).thenReturn(Optional.empty());

        Assertions.assertThrows(ArtistNotFoundException.class, () -> {
            artistService.getArtistByName("Cujo");
        });
    }
}