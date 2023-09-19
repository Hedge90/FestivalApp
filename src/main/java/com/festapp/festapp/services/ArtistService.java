package com.festapp.festapp.services;

import com.festapp.festapp.dtos.ArtistDTO;
import com.festapp.festapp.dtos.NewArtistDTO;

public interface ArtistService {

    public ArtistDTO getArtistByName(String name);

    public ArtistDTO createNewArtist(NewArtistDTO newArtistDTO);

}
