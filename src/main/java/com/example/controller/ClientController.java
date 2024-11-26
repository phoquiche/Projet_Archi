package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.business.clients.modele.Client;
import com.example.business.clients.ClientsService;

@RestController
@RequestMapping("/api/")
public class ClientController {

    @GetMapping
    public String home() {
        return "Welcome to the bank";
    }


    @PutMapping("/add")
    public ResponseEntity<String> addClient(@RequestBody Client client) {
        ClientsService service = new ClientsService();
        service.create(client.getNom(), client.getPrenom(), client.getEmail());
        return new ResponseEntity<>("Client added", HttpStatus.OK);
    }

    @GetMapping("/clients/{email}")
    public ResponseEntity<String> getClient(@PathVariable String email) {
        ClientsService service = new ClientsService();
        if (service.get(email) == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.get(email).toString(), HttpStatus.OK);
    }

    @DeleteMapping("/clients/{email}")
    public ResponseEntity<String> deleteClient(@PathVariable String email) {
        return new ResponseEntity<>("Client deleted", HttpStatus.OK);
    }

    @PutMapping("/clients/{email}")
    public ResponseEntity<String> updateClient(@PathVariable String email, @RequestBody String newEmail) {
        return new ResponseEntity<>("Client updated", HttpStatus.OK);
    }


}
