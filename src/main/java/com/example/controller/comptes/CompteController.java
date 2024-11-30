package com.example.controller.comptes;

import com.example.business.clients.ClientsService;
import com.example.business.clients.modele.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.business.comptes.modele.Compte;
import com.example.business.comptes.ComptesService;

@RestController
@RequestMapping("/api/comptes/")
public class CompteController {

    private final ComptesService service;
    private final ClientsService clientsService;

    public CompteController() {
        this.service = new ComptesService();
        this.clientsService = new ClientsService();
    }

    @GetMapping
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Welcome to the bank", HttpStatus.OK);
    }

    @GetMapping("/{emailClient}/{nomCompte}")
    public ResponseEntity<String> getCompte(@PathVariable String emailClient, @PathVariable String nomCompte) {
        if (clientsService.get(emailClient) == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        if (!service.compteExist(emailClient, nomCompte)) {
            return new ResponseEntity<>("Compte not found", HttpStatus.NOT_FOUND);
        }
        Compte compte = service.getCompte(emailClient, nomCompte);
        return new ResponseEntity<>(compte.toString(), HttpStatus.OK);
    }

    @PutMapping("/{emailClient}/{nomCompte}")
    public ResponseEntity<String> addCompte(@PathVariable String emailClient, @PathVariable String nomCompte) {
        if (clientsService.get(emailClient) == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        if (service.compteExist(emailClient, nomCompte)) {
            return new ResponseEntity<>("Compte already exists", HttpStatus.CONFLICT);
        }
        service.addCompte(emailClient, nomCompte);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{emailClient}/{nomCompte}/{operations}")
    public ResponseEntity<String> updateCompte(@PathVariable String emailClient, @PathVariable String nomCompte, @PathVariable double operations) {
        if (clientsService.get(emailClient) == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        if (!service.compteExist(emailClient, nomCompte)) {
            return new ResponseEntity<>("Compte not found", HttpStatus.NOT_FOUND);
        }
        service.updateCompte(emailClient, nomCompte, operations);
        return new ResponseEntity<>("Compte updated, nouveau solde: " + service.getCompte(emailClient, nomCompte).getSolde(), HttpStatus.OK);
    }

    @DeleteMapping("/{emailClient}/{nomCompte}")
    public ResponseEntity<String> deleteCompte(@PathVariable String emailClient, @PathVariable String nomCompte) {
        if (clientsService.get(emailClient) == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        if (!service.compteExist(emailClient, nomCompte)) {
            return new ResponseEntity<>("Compte not found", HttpStatus.NOT_FOUND);
        }
        service.deleteCompte(emailClient, nomCompte);
        return new ResponseEntity<>("Compte deleted", HttpStatus.OK);
    }
}