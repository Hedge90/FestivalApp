package com.festapp.festapp.services;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.dtos.NewArtistDTO;

public interface ArtistService {

    ArtistDTO getArtistByName(String name);

    ArtistDTO createNewArtist(NewArtistDTO newArtistDTO);
}
