package com.example.controller.clients;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.business.clients.modele.Client;
import com.example.business.clients.ClientsService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientsService service;

    public ClientController() {
        this.service = new ClientsService();

    }

    @GetMapping
    public String home() {
        return "Bienvenue chez Arnakbank";
    }


    @PutMapping("/add")
    public ResponseEntity<String> addClient(@RequestBody Client client) {
        if (client.getNom() == null || client.getPrenom() == null || client.getEmail() == null) {
            return new ResponseEntity<>("400 mauvaise requête", HttpStatus.BAD_REQUEST);
        }
        Pattern emailPattern = Pattern.compile("^(.+)@(\\\\S+)$");
        Matcher emailMatcher = emailPattern.matcher(client.getEmail());
        if (!emailMatcher.matches()) {
            return new ResponseEntity<>("400 mauvaise requête, merci de former une adresse email valide", HttpStatus.BAD_REQUEST);
        }
        if(service.create(client.getNom(), client.getPrenom(), client.getEmail()) == null) {
            return new ResponseEntity<>("409 client déjà existant", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("201 Client ajouté", HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<String> getClient(@PathVariable String email) {
        if (service.get(email) == null) {
            return new ResponseEntity<>("404 client non trouvé", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.get(email).toString(), HttpStatus.OK);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteClient(@PathVariable String email) {
        if (!service.delete(email)) {
            return new ResponseEntity<>("404 client non trouvé", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("200 Client supprimé", HttpStatus.OK);
    }

    @PatchMapping("/{email}")
    public ResponseEntity<String> updateClient(@PathVariable String email, @RequestBody Client client){
        if (!service.update(email, client.getNom(), client.getPrenom(), client.getEmail())) {
            return new ResponseEntity<>("404 client non trouvé", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("200 Client mis à jour", HttpStatus.OK);
    }
}
