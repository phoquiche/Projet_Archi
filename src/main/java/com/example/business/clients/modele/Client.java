package com.example.business.clients.modele;

import com.example.business.comptes.modele.Compte;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Client {
    private final UUID id = UUID.randomUUID();
    private String nom;
    private String prenom;
    private String email;
    private final ArrayList<Compte> comptes = new ArrayList<>();



    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }
    public UUID getId() {
        return id;
    }

    public Client(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Client(String nom, String prenom, String email, ArrayList<Compte> comptes) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.comptes.addAll(comptes);
    }

    public ArrayList<Compte> getComptes() {
        return comptes;
    }
    public String toString() {
        String comptes = "";
        for (Compte compte : this.comptes) {
            comptes += compte.toString() + " ";
        }
        return "Nom: " + nom + " Prenom: " + prenom + " Email: " + email + " Comptes: " + comptes;
    }

    public void addCompte(String nomCompte) {
        comptes.add(new Compte(nomCompte));
    }

    public void updateCompte(String nomCompte, double operations) {
        for (Compte compte : comptes) {
            if (compte.getNom().equals(nomCompte)) {
                compte.setSolde(compte.getSolde() + operations);
            }
        }
    }
    public void deleteCompte(String nomCompte) {
        comptes.removeIf(compte -> compte.getNom().equals(nomCompte));

    }
}
