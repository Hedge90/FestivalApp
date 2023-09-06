package com.festapp.festapp.dtos;

import java.time.LocalDateTime;

public class ArtistDTO {

    private String name;

    private LocalDateTime date;

    public ArtistDTO() {}

    public ArtistDTO(String name, LocalDateTime date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
