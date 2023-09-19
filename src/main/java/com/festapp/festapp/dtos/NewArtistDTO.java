package com.festapp.festapp.dtos;

import java.time.LocalDateTime;

public class NewArtistDTO {

    private String name;

    private LocalDateTime date;

    private String day;

    public NewArtistDTO() {}

    public NewArtistDTO(String name, LocalDateTime date, String day) {
        this.name = name;
        this.date = date;
        this.day = day;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
