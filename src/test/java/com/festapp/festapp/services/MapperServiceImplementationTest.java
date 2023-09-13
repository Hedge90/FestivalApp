package com.festapp.festapp.services;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.dtos.NewArtistDTO;
import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.entities.Artist;
import com.festapp.festapp.entities.Day;
import com.festapp.festapp.enums.DayName;
import com.festapp.festapp.exceptions.NoSuchDayExistsException;
import com.festapp.festapp.repositories.DayRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class MapperServiceImplementationTest {

    private MapperServiceImplementation mapperServiceImplementation;

    private DayRepository dayRepository;

    @Autowired
    MapperServiceImplementationTest() {
        dayRepository = Mockito.mock(DayRepository.class);
        this.mapperServiceImplementation = new MapperServiceImplementation(dayRepository);
    }

    @Test
    void convertArtistToArtistDTO_withProperArtist_returnsCorrectArtistDTO() {
        LocalDateTime date = LocalDateTime.now();
        Day day = new Day();
        Artist artist = new Artist("Kozso", date, day);
        ArtistDTO artistDTO = mapperServiceImplementation.convertArtistToArtistDTO(artist);

        Assertions.assertEquals("Kozso", artistDTO.getName());
    }

    @Test
    void convertNewArtistDTOTOArtist_withProperArtistDTO_returnsCorrectArtist() {
        LocalDateTime date = LocalDateTime.now();
        Day friday = new Day();
        NewArtistDTO newArtistDTO = new NewArtistDTO("Kozso", date, "Friday");
        Mockito.when(dayRepository.findDayByName(DayName.valueOf("FRIDAY"))).thenReturn(Optional.of(friday));
        Artist artist = mapperServiceImplementation.convertNewArtistDTOToArtist(newArtistDTO);

        Assertions.assertEquals("Kozso", artist.getName());
        Assertions.assertEquals(date, artist.getDate());
        Assertions.assertEquals(friday, artist.getDay());
    }

    @Test
    void convertNewArtistDTOTOArtist_withDayNotExistingInDatabase_throwsNoSuchDayExistsException() {
        LocalDateTime date = LocalDateTime.now();
        NewArtistDTO newArtistDTO = new NewArtistDTO("Kozso", date, "Friday");
        Mockito.when(dayRepository.findDayByName(DayName.valueOf("FRIDAY"))).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchDayExistsException.class, () -> {
            mapperServiceImplementation.convertNewArtistDTOToArtist(newArtistDTO);
        });
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