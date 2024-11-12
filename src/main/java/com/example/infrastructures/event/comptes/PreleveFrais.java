package com.example.infrastructures.event.comptes;

import java.time.*;
import java.util.HashMap;
import com.example.business.*;
import com.example.business.clients.modele.Client;
import com.example.business.comptes.ComptesService;
import com.example.business.comptes.modele.Compte;
import com.example.infrastructures.bdd.client.modele.MongoQueries;


public class PreleveFrais {

    private double Frais = 12.0;

    boolean preleveFrais(){
        LocalDate date = LocalDate.now();
        if(date.getDayOfMonth() == 1){
            MongoQueries mongoQueries = new MongoQueries();
            ComptesService comptesService = new ComptesService();
            for (Client client : mongoQueries.getAllClients()) {
                for (Compte compte : client.getComptes()) {
                    if (compte.getMontant() > Frais) {
                        HashMap<String, Double> operations = new HashMap<>();
                        operations.put("EUR", -Frais);
                        comptesService.updateCompte(client.getEmail(), compte.getNom(), operations);
                    }
                }
            }
            return true;
        }
        return false;
    }



}
