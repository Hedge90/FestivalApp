package com.festapp.festapp.services;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.dtos.NewArtistDTO;
import com.festapp.festapp.dtos.NewDayDTO;
import com.festapp.festapp.dtos.NewOrganizerResponseDTO;
import com.festapp.festapp.entities.Artist;
import com.festapp.festapp.entities.Day;
import com.festapp.festapp.entities.Organizer;
import com.festapp.festapp.enums.DayName;
import com.festapp.festapp.exceptions.NoSuchDayExistsException;
import com.festapp.festapp.repositories.DayRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperServiceImplementation implements MapperService {

    private ModelMapper modelMapper;

    private DayRepository dayRepository;

    @Autowired
    public MapperServiceImplementation(DayRepository dayRepository) {
        modelMapper = new ModelMapper();
        this.dayRepository = dayRepository;
    }

    @Override
    public ArtistDTO convertArtistToArtistDTO(Artist artist) {
        return modelMapper.map(artist, ArtistDTO.class);
    }

    @Override
    public Artist convertNewArtistDTOToArtist(NewArtistDTO newArtistDTO) {
        try {
            modelMapper.typeMap(NewArtistDTO.class, Artist.class).addMappings(mapper -> {
                mapper.map(src -> dayRepository.findDayByName(DayName.valueOf(newArtistDTO.getDay().toUpperCase())).get(),
                        Artist::setDay);
            });
        } catch (RuntimeException e) {
            throw new NoSuchDayExistsException();
        }
        return modelMapper.map(newArtistDTO, Artist.class);
    }

    @Override
    public NewDayDTO convertDayToNewDayDTO(Day day) {
        return modelMapper.map(day, NewDayDTO.class);
    }

    @Override
    public NewOrganizerResponseDTO convertOrganizerToNewOrganizerResponseDTO(Organizer organizer) {
        return modelMapper.map(organizer,NewOrganizerResponseDTO.class);
    }

}
