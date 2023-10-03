package com.festapp.festapp.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.festapp.festapp.entities.Day;

import java.time.LocalDateTime;

public class ArtistDTO {

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime date;

    private Day day;

    public ArtistDTO() {}

    public ArtistDTO(String name, LocalDateTime date, Day day) {
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

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
