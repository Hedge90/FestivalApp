package com.festapp.festapp.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public class NewOrganizerDTO {
    @NotEmpty(message = "Name must be included")
    private String name;

    @Column(unique = true)
    @Email(message = "Valid Email is required")
    @NotEmpty(message = "Email must be included")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z]).{6,}$", message = "Password must be at least 6 characters long and contain at least one capital letter and one non-alphabetical character")
    @NotEmpty(message = "Password must be included")
    private String password;

    public NewOrganizerDTO() {
    }

    public NewOrganizerDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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
