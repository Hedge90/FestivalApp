package com.festapp.festapp.services;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.dtos.NewOrganizerDTO;
import com.festapp.festapp.dtos.NewOrganizerResponseDTO;
import com.festapp.festapp.entities.Artist;
import com.festapp.festapp.entities.Day;
import com.festapp.festapp.entities.Organizer;

public interface MapperService {
    ArtistDTO convertArtistToArtistDTO(Artist artist);

    NewDayDTO convertDayToNewDayDTO(Day day);

    NewOrganizerResponseDTO convertOrganizerToNewOrganizerResponseDTO(Organizer organizer);
}
