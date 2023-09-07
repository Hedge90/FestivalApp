package com.festapp.festapp.services;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.entities.Artist;
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
}
