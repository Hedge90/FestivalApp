package com.festapp.festapp.dtos;

public class AuthenticationResponseDTO {
    private String status;
    private String token;

    public AuthenticationResponseDTO(String status, String token) {
        this.status = status;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }
}
