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

    public String getNom() {
        return nom;
    }

    public void setMontant(double montant) {
        this.solde = montant;
    }

    public double getMontant() {
        return solde;
    }

}
