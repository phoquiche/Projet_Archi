package com.example.business.comptes;

import com.example.business.clients.modele.Client;
import com.example.business.comptes.modele.Compte;
import com.example.infrastructures.bdd.client.modele.MongoQueries;
import java.util.ArrayList;

public class ComptesService {

    private final MongoQueries mongoQueries;

    public ComptesService() {
        mongoQueries = new MongoQueries();
    }


    public boolean compteExist(String email, String nomCompte) {
        ArrayList<Compte> comptes = mongoQueries.getComptes(email);
        for (Compte compte : comptes) {
            if (compte.getNom().equals(nomCompte)) {
                return true;
            }
        }
        return false;
    }

    public Compte getCompte(String email, String nomCompte) {
        ArrayList<Compte> comptes = mongoQueries.getComptes(email);
        for (Compte compte : comptes) {
            if (compte.getNom().equals(nomCompte)) {
                return compte;
            }
        }
        return null;
    }

    public boolean addCompte(String email, String nomCompte) {
        Client client = mongoQueries.getClient(email);
        client.addCompte(nomCompte);
        mongoQueries.addCompte(email, nomCompte);
        return true;
    }

    public void updateCompte(String email, String nomCompte, double operations) {
        Client client = mongoQueries.getClient(email);
        client.updateCompte(nomCompte, operations);
        mongoQueries.updateCompte(email, nomCompte, operations);
    }

    public void deleteCompte(String email, String nomCompte) {
        Client client = mongoQueries.getClient(email);
        client.deleteCompte(nomCompte);
        mongoQueries.updateClient(email, client);
    }
}
