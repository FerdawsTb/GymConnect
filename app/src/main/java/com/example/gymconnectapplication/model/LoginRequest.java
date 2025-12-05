package com.example.gymconnectapplication.model;

public class LoginRequest {
    private String email;
    private String motDePasse; // Important : doit être le même nom que dans ton DTO Spring Boot

    public LoginRequest(String email, String motDePasse) {
        this.email = email;
        this.motDePasse = motDePasse;
    }
}