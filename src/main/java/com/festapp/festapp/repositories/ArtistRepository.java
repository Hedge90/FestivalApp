package com.festapp.festapp.repositories;

import com.festapp.festapp.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    @Query("SELECT a FROM Artist a WHERE a.name = :artistName")
    Optional<Artist> findArtistByName(String artistName);
}
