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



    public Object getPrenom() {
        return prenom;
    }

    public Object getNom() {
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

    public ArrayList<Compte> getComptes() {
        return comptes;
    }

    public void addCompte(String nomCompte) {
        comptes.add(new Compte(nomCompte));
    }

    public void updateCompte(String nomCompte, HashMap<String, Double> operations) {
        for (Compte compte : comptes) {
            if (compte.getNom().equals(nomCompte)) {
                for (String operation : operations.keySet()) {
                    switch (operation) {
                        case "EUR":
                            compte.setMontant(compte.getMontant()+operations.get(operation));
                            break;
                        case "LIV":
                            compte.setMontant(compte.getMontant()+operations.get(operation)*0.83);
                            break;
                        case "USD":
                            compte.setMontant(compte.getMontant()+operations.get(operation)*1.06);
                            break;


                    }
                }
            }
        }
    }
}
