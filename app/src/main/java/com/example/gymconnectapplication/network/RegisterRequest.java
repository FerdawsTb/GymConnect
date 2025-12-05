package com.example.gymconnectapplication.network;

public class RegisterRequest {
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String telephone;
    private String sexe;
    private Integer age;
    private Double poids;
    private Double taille;

    public RegisterRequest(String nom, String prenom, String email, String motDePasse, String telephone, String sexe, Integer age, Double poids, Double taille) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.sexe = sexe;
        this.age = age;
        this.poids = poids;
        this.taille = taille;
    }
}