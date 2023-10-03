package com.festapp.festapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table (name = "organizers")
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name must be included")
    private String name;
    @Column(unique = true)
    @Email(message = "Valid Email is required!")
    @NotEmpty(message = "Email must be included")
    private  String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z]).{6,}$", message = "Password must be at least 6 characters long and contain at least one capital letter and one non-alphabetical character")
    private String password;

    public Organizer() {
    }

    public Organizer(String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
