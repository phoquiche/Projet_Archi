package com.example.business.comptes;

import com.example.business.clients.modele.Client;
import com.example.business.comptes.modele.Compte;
import com.example.infrastructures.bdd.client.modele.MongoQueries;

import java.util.HashMap;

public class ComptesService {

    private MongoQueries mongoQueries;

    public boolean compteExists(String email, String nomCompte) {
        Client client = mongoQueries.getClient(email);
        for (Compte compte : client.getComptes()) {
            if (compte.getNom().equals(nomCompte)) {
                return true;
            }
        }
        return false;
    }

    public void addCompte(String email, String nomCompte) {
        Client client = mongoQueries.getClient(email);
        client.addCompte(nomCompte);
        mongoQueries.updateClient(client);
    }

    public void updateCompte(String email, String nomCompte, HashMap<String, Double> operations) {
        Client client = mongoQueries.getClient(email);
        client.updateCompte(nomCompte, operations);
        mongoQueries.updateClient(client);
    }
}
