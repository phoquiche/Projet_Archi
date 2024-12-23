package com.example.business.comptes.modele;

import java.util.UUID;

public class Compte {

    private final UUID id = UUID.randomUUID();
    private double solde = 0;
    private String nom;
    private String devise = "EUR";

    public Compte(String nom) {
        this.nom = nom;
    }

    public Compte(String nom, double solde) {
        this.nom = nom;
        this.solde = solde;
    }
    public UUID getId() {
        return id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }


    public String toString() {
        return "Nom: " + nom + " Montant: " + solde + " Devise: " + devise;
    }
}
