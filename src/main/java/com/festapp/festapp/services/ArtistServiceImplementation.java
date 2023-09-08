package com.festapp.festapp.services;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.entities.Artist;
import com.festapp.festapp.exceptions.ArtistNotFoundException;
import com.festapp.festapp.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtistServiceImplementation implements ArtistService {

    private ArtistRepository artistRepository;

    private MapperService mapperService;
    @Autowired
    public ArtistServiceImplementation(ArtistRepository artistRepository, MapperService mapperService) {
        this.artistRepository = artistRepository;
        this.mapperService = mapperService;
    }

    @Override
    public ArtistDTO getArtistByName(String name) {
        Optional<Artist> optionalArtist = artistRepository.findArtistByName(name);
        if (optionalArtist.isPresent()) {
            return mapperService.convertArtistToArtistDTO(optionalArtist.get());
        } else {
            throw new ArtistNotFoundException();
        }
    }
}
