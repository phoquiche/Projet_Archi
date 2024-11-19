package com.example.infrastructures.bdd.client.modele;

import com.example.business.clients.modele.Client;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
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
        return new Client(document.getString("nom"), document.getString("prenom"), document.getString("email"));
    }

    public void insertClient(Client client) {
        Document document = new Document("nom", client.getNom())
                .append("prenom", client.getPrenom())
                .append("email", client.getEmail())
                .append("id", client.getId().toString())
                .append("comptes", client.getComptes());
        collection.insertOne(document);
    }

    public void updateClient(Client client) {
        Document document = new Document("nom", client.getNom())
                .append("prenom", client.getPrenom())
                .append("email", client.getEmail())
                .append("id", client.getId().toString())
                .append("comptes", client.getComptes());
        collection.updateOne(new Document("email", client.getEmail()), new Document("$set", document));
    }

    public void deleteClient(String email) {
        collection.deleteOne(new Document("email", email));
    }


    public Collection<Client> getAllClients() {
        return collection.find().map(document -> new Client(document.getString("nom"), document.getString("prenom"), document.getString("email"))).into(new ArrayList<>());
    }
}