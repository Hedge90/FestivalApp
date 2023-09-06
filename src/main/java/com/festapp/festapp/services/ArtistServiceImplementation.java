package com.festapp.festapp.services;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.entities.Artist;
import com.festapp.festapp.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtistServiceImplementation implements ArtistService {

    private ArtistRepository artistRepository;
    @Autowired
    public ArtistServiceImplementation(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public ArtistDTO getArtistByName(String name) {
        Optional<Artist> optionalArtist = artistRepository.findArtistByName(name);
        if (optionalArtist.isPresent()) {
            Artist artist = optionalArtist.get();
            return new ArtistDTO(artist.getName(), artist.getDate());
        }
        return null;
    }
}
