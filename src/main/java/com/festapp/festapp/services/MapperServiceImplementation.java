package com.festapp.festapp.services;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.dtos.NewOrganizerDTO;
import com.festapp.festapp.dtos.NewOrganizerResponseDTO;
import com.festapp.festapp.entities.Artist;
import com.festapp.festapp.entities.Day;
import com.festapp.festapp.entities.Organizer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MapperServiceImplementation implements MapperService {

    private ModelMapper modelMapper;

    @Autowired
    public MapperServiceImplementation() {
        modelMapper = new ModelMapper();
    }

    @Override
    public ArtistDTO convertArtistToArtistDTO(Artist artist) {
        return modelMapper.map(artist, ArtistDTO.class);
    }

    @Override
    public NewDayDTO convertDayToNewDayDTO(Day day) {
        return modelMapper.map(day,NewDayDTO.class);
    }

    @Override
    public NewOrganizerResponseDTO convertOrganizerToNewOrganizerResponseDTO(Organizer organizer) {
        return modelMapper.map(organizer,NewOrganizerResponseDTO.class);
    }

}
