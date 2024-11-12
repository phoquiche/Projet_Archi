package com.example.business.clients;

import com.example.business.clients.modele.Client;
import com.example.infrastructures.bdd.client.modele.MongoQueries;

import java.util.Collection;
import java.util.HashMap;

public class ClientsService {

    private MongoQueries mongoQueries;

    public Collection<Client> getAll() {
        return mongoQueries.getAllClients();
    }

    public Client get(String email) {
        return mongoQueries.getClient(email);
    }

    public Client create(String nom, String prenom, String email) {
        Client client = new Client(nom, prenom, email);
        if (mongoQueries.clientExists(email)) {
            return null;
        }
        mongoQueries.insertClient(client);
        return client;
    }

    public void update(String nom, String prenom, String email) {
        Client client = new Client(nom, prenom, email);
        mongoQueries.updateClient(client);
    }

    public void delete(String email) {
        mongoQueries.deleteClient(email);
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
