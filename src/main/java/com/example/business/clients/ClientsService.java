package com.example.business.clients;

import com.example.business.clients.modele.Client;
import com.example.infrastructures.bdd.client.modele.MongoQueries;


public class ClientsService {

    private final MongoQueries mongoQueries;

    public ClientsService() {
        mongoQueries = new MongoQueries();
    }


    public Client get(String email) {
        return mongoQueries.getClient(email);
    }

    public boolean clientExists(String email) {
        return mongoQueries.clientExists(email);
    }

    public Client create(String nom, String prenom, String email) {
        Client client = new Client(nom, prenom, email);

        if (clientExists(email)) {
            return null;
        }
        mongoQueries.insertClient(client);
        return client;
    }

    public boolean update(String oldEmail, String nom, String prenom, String email) {
        Client client = new Client(nom, prenom, email);
        if (!clientExists(oldEmail)) {
            return false;
        }
        if (!clientExists(email)) {
            return false;
        }
        mongoQueries.updateClient(oldEmail,client);
        return true;
    }

    public boolean delete(String email) {
        if (!clientExists(email)) {
            return false;
        }
        mongoQueries.deleteClient(email);
        return true;
    }







}
