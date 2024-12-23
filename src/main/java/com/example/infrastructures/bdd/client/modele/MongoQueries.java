package com.example.infrastructures.bdd.client.modele;

import com.example.business.clients.modele.Client;
import com.example.business.comptes.modele.Compte;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;


@Component
public class MongoQueries {

    private final MongoCollection<Document> collection;


    public MongoQueries() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("projet");
        this.collection = mongoDatabase.getCollection("clients");
    }

    public boolean clientExists(String email) {
        return collection.find(new Document("email", email)).first() != null;
    }

    public Client getClient(String email) {
        Document document = collection.find(new Document("email", email)).first();
        if (document == null) {
            return null;
        }
        ArrayList<Compte> comptes = new ArrayList<>();
        for (Document compte : (ArrayList<Document>) document.get("comptes")) {

            comptes.add(new Compte(compte.getString("nom"), compte.getDouble("solde")));
        }

        return new Client(document.getString("nom"), document.getString("prenom"), document.getString("email"), comptes);
    }

    public void insertClient(Client client) {
        Document document = new Document("nom", client.getNom())
                .append("prenom", client.getPrenom())
                .append("email", client.getEmail())
                .append("id", client.getId().toString())
                .append("comptes", client.getComptes());
        collection.insertOne(document);
    }

    public void updateClient(String oldEmail, Client client) {
        Document document = new Document("nom", client.getNom())
                .append("prenom", client.getPrenom())
                .append("email", client.getEmail())
                .append("comptes", client.getComptes());
        collection.updateOne(new Document("email", oldEmail), new Document("$set", document));
    }
    public boolean addCompte(String email, String nomCompte) {
        Document document = collection.find(new Document("email", email)).first();
        if (document == null) {
            return false;
        }
        ArrayList<Document> comptes = (ArrayList<Document>) document.get("comptes");
        comptes.add(new Document("nom", nomCompte).append("solde", 0.0));
        collection.updateOne(new Document("email", email), new Document("$set", new Document("comptes", comptes)));
        return true;
    }

    public void deleteClient(String email) {
        collection.deleteOne(new Document("email", email));
    }


    public Collection<Client> getAllClients() {
        return collection.find().map(document -> new Client(document.getString("nom"), document.getString("prenom"), document.getString("email"))).into(new ArrayList<>());
    }

    public ArrayList<Compte> getComptes(String email) {
        Document document = collection.find(new Document("email", email)).first();
        if (document == null) {
            return null;
        }
        ArrayList<Document> comptes = (ArrayList<Document>) document.get("comptes");
        ArrayList<Compte> comptesList = new ArrayList<>();
        for (Document compte : comptes) {
            comptesList.add(new Compte(compte.getString("nom"), compte.getDouble("solde")));
        }
        return comptesList;
    }

    public void updateCompte(String email, String nomCompte, double operations) {
        Document document = collection.find(new Document("email", email)).first();
        if (document == null) {
            return;
        }
        ArrayList<Document> comptes = (ArrayList<Document>) document.get("comptes");
        for (Document compte : comptes) {
            if (compte.getString("nom").equals(nomCompte)) {
                compte.put("solde", compte.getDouble("solde") + operations);
            }
        }
        collection.updateOne(new Document("email", email), new Document("$set", new Document("comptes", comptes)));
    }

}