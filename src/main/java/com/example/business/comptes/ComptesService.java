package com.example.business.comptes;

import com.example.business.clients.modele.Client;
import com.example.business.comptes.modele.Compte;
import com.example.infrastructures.bdd.client.modele.MongoQueries;

import java.util.HashMap;

public class ComptesService {

    private final MongoQueries mongoQueries;

    public ComptesService() {
        mongoQueries = new MongoQueries();
    }

    public boolean compteExists(String email, String nomCompte) {
        Client client = mongoQueries.getClient(email);
        for (Compte compte : client.getComptes()) {
            if (compte.getNom().equals(nomCompte)) {
                return true;
            }
        }
        return false;
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
        mongoQueries.updateClient(email, client);
    }
}
