package com.example.infrastructures.event.comptes;
import com.example.business.clients.modele.Client;
import com.example.business.comptes.modele.Compte;
import com.example.infrastructures.bdd.client.modele.MongoQueries;


public class PreleveFrais {

    public void preleveFrais() {
        MongoQueries mongoQueries = new MongoQueries();
        for (Client client : mongoQueries.getAllClients()) {
            for (Compte compte : client.getComptes()) {
                if (compte.getNom().equals("courant")) {
                    double frais = 12.0;
                    compte.setSolde(compte.getSolde() - frais);
                    mongoQueries.updateCompte(client.getEmail(), compte.getNom(), -frais);
                }
            }
        }
    }
}
