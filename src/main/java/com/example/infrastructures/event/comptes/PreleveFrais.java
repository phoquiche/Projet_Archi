package com.example.infrastructures.event.comptes;
import com.example.business.clients.modele.Client;
import com.example.business.comptes.modele.Compte;
import com.example.infrastructures.bdd.client.modele.MongoQueries;


public class PreleveFrais {

    private double Frais = 12.0;

    public void preleveFrais() {
        MongoQueries mongoQueries = new MongoQueries();
        for (Client client : mongoQueries.getAllClients()) {
            for (Compte compte : client.getComptes()) {
                if (compte.getNom().equals("courant")) {
                    compte.setSolde(compte.getSolde() - Frais);
                    mongoQueries.updateCompte(client.getEmail(), compte.getNom(), -Frais);
                }
            }
        }
    }
}
