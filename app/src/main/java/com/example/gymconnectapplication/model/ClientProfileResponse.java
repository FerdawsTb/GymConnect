package com.example.gymconnectapplication.model;

public class ClientProfileResponse {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private int age;
    private double poids;
    private double taille;

    // Getters (nécessaires pour afficher les données)
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public int getAge() { return age; }
    public double getPoids() { return poids; }
    public double getTaille() { return taille; }
}
