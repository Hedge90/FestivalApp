package com.festapp.festapp.services;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.dtos.NewArtistDTO;
import com.festapp.festapp.entities.Artist;
import com.festapp.festapp.entities.Day;
import com.festapp.festapp.exceptions.ArtistAlreadyExistsException;
import com.festapp.festapp.exceptions.ArtistNotFoundException;
import com.festapp.festapp.repositories.ArtistRepository;
import com.festapp.festapp.repositories.DayRepository;
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

    DayRepository dayRepository;

    MapperService mapperService;

    @Autowired
    ArtistServiceImplementationTest() {
        artistRepository = Mockito.mock(ArtistRepository.class);
        dayRepository = Mockito.mock(DayRepository.class);
        mapperService = Mockito.mock(MapperService.class);
        this.artistService = new ArtistServiceImplementation(artistRepository, dayRepository, mapperService);
    }

    @Test
    void getArtistByName_withInputMatchingExistingArtist_returnsCorrectArtistDTO() {
        LocalDateTime date = LocalDateTime.now();
        Artist fakeArtist = new Artist("Kozso", date, new Day());
        fakeArtist.setDate(date);
        ArtistDTO artistDTO = new ArtistDTO("Kozso", date, new Day());
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

    @Test
    void createNewArtist_withProperNewArtistDTO_returnsProperArtistDTO() {
        LocalDateTime date = LocalDateTime.now();
        Day friday = new Day();
        NewArtistDTO newArtistDTO = new NewArtistDTO("Kozso", date, "Friday");
        Artist artist = new Artist("Kozso", date, friday);
        ArtistDTO artistDTO = new ArtistDTO("Kozso", date, friday);
        Mockito.when(artistRepository.findArtistByName("Kozso")).thenReturn(Optional.empty());
        Mockito.when(mapperService.convertNewArtistDTOToArtist(newArtistDTO)).thenReturn(artist);
        Mockito.when(artistRepository.save(artist)).thenReturn(artist);
        Mockito.when(mapperService.convertArtistToArtistDTO(artist)).thenReturn(artistDTO);
        ArtistDTO storedArtist = artistService.createNewArtist(newArtistDTO);

        Assertions.assertEquals("Kozso", storedArtist.getName());
        Assertions.assertEquals(date, storedArtist.getDate());
        Assertions.assertEquals(friday, storedArtist.getDay());
    }

    @Test
    void createNewArtist_withArtistUnderExitingName_throwsArtistAlreadyExistsException() {
        LocalDateTime date = LocalDateTime.now();
        NewArtistDTO newArtistDTO = new NewArtistDTO("Kozso", date, "Friday");
        Artist storedArtist = new Artist("Kozso", LocalDateTime.now(), new Day());
        Mockito.when(artistRepository.findArtistByName("Kozso")).thenReturn(Optional.of(storedArtist));

        Assertions.assertThrows(ArtistAlreadyExistsException.class, () -> {
            artistService.createNewArtist(newArtistDTO);
        });
    }
}