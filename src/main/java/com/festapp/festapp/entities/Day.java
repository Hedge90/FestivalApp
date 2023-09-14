package com.festapp.festapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.festapp.festapp.enums.DayName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "days")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "day", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JsonBackReference
    private List<Artist> artists = new ArrayList<>();
    @NotNull(message = "Date must be included!")
    @Column(unique = true)
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Name must be included")
    @Column(unique = true)
    private DayName name;


    public Day() {
        this.artists = new ArrayList<>();
    }

    public Day(LocalDate date, DayName name) {
        this.date = date;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Artist artists) {
        this.artists.add(artists);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public DayName getName() {
        return name;
    }

    public void setName(DayName name) {
        this.name = name;
    }
}
