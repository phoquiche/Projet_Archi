package com.example.business.clients;

import com.example.business.clients.modele.Client;
import com.example.infrastructures.bdd.client.modele.MongoQueries;

import java.util.Collection;
import java.util.HashMap;

public class ClientsService {

    private final MongoQueries mongoQueries;

    public ClientsService() {
        mongoQueries = new MongoQueries();
    }

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

    public boolean update(String oldEmail, String nom, String prenom, String email) {
        Client client = new Client(nom, prenom, email);
        if (!mongoQueries.clientExists(oldEmail)) {
            return false;
        }
        if (!mongoQueries.clientExists(email)) {
            return false;
        }
        mongoQueries.updateClient(oldEmail,client);
        return true;
    }

    public boolean delete(String email) {
        if (!mongoQueries.clientExists(email)) {
            return false;
        }
        mongoQueries.deleteClient(email);
        return true;
    }



    public void updateCompte(String email, String nomCompte, double operations) {
        Client client = mongoQueries.getClient(email);
        client.updateCompte(nomCompte, operations);
        mongoQueries.updateClient(email, client);
    }



}
