package com.festapp.festapp.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class NewArtistDTO {
    @NotEmpty(message = "Name must be included")
    private String name;

    @NotNull(message = "date must be included")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private LocalDateTime date;

    private String bio;

    @NotEmpty(message = "day must be included")
    private String day;

    public NewArtistDTO() {}

    public NewArtistDTO(String name, LocalDateTime date,String day) {
        this.name = name;
        this.date = date;
        this.day = day;
    }

    public NewArtistDTO(String name, LocalDateTime date,String bio ,String day) {
        this.name = name;
        this.date = date;
        this.bio = bio;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
