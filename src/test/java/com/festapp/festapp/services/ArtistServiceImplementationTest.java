package com.festapp.festapp.services;

import com.festapp.festapp.entities.Artist;
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

    @Autowired
    ArtistServiceImplementationTest(ArtistServiceImplementation artistService) {
        this.artistService = artistService;
        artistRepository = Mockito.mock(ArtistRepository.class);
    }

    @Test
    void getArtistByName_withInputMatchingExistingArtist_returnsCorrectArtistDTO() {
        LocalDateTime date = LocalDateTime.now();
        Artist fakeArtist = new Artist("Kozso");
        fakeArtist.setDate(date);
        Mockito.when(artistRepository.findArtistByName("Kozso")).thenReturn(Optional.of(fakeArtist));

        Artist storedArtist = artistRepository.findArtistByName("Kozso").get();

        Assertions.assertEquals("Kozso", storedArtist.getName());
        Assertions.assertEquals(date, storedArtist.getDate());
    }
}