package com.festapp.festapp.services;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.entities.Artist;

public interface MapperService {

    public ArtistDTO convertArtistToArtistDTO(Artist artist);
}
