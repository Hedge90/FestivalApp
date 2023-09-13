package com.festapp.festapp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class AuthenticationRequestDTO {
    @Email(message = "Email is invalid")
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z]).{6,}$", message = "Password is invalid")
    private String password;

    public AuthenticationRequestDTO() {
    }

    public AuthenticationRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
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
