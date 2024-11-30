package com.example.controller.comptes;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.business.comptes.modele.Compte;
import com.example.business.comptes.ComptesService;

@RestController
@RequestMapping("/api/comptes/")
public class CompteController {

    @GetMapping
    public  ResponseEntity<String> home() {
        return new ResponseEntity<>("Welcome to the bank", HttpStatus.OK);
    }

    @GetMapping("/{emailClient}/{nomCompte}")
    public ResponseEntity<String> getCompte(@PathVariable String emailClient, @PathVariable String nomCompte) {
        ComptesService service = new ComptesService();
        if (!service.compteExist(emailClient, nomCompte)) {
            return new ResponseEntity<>("Compte not found", HttpStatus.NOT_FOUND);
        }
        Compte compte = service.getCompte(emailClient, nomCompte);
        return new ResponseEntity<>(compte.toString(), HttpStatus.OK);
    }

    @PutMapping("/{emailClient}/{nomCompte}")
    public ResponseEntity<String> addCompte(@PathVariable String emailClient, @PathVariable String nomCompte) {
        ComptesService service = new ComptesService();
        if (service.compteExist(emailClient, nomCompte)) {
            return new ResponseEntity<>("Compte already exists", HttpStatus.CONFLICT);
        }
        service.addCompte(emailClient, nomCompte);
        return new ResponseEntity<>("Compte added", HttpStatus.CREATED);
    }

    @PatchMapping("/{emailClient}/{nomCompte}/{operations}")
    public ResponseEntity<String> updateCompte(@PathVariable String emailClient, @PathVariable String nomCompte, @PathVariable double operations) {
        ComptesService service = new ComptesService();
        if (!service.compteExist(emailClient, nomCompte)) {
            return new ResponseEntity<>("Compte not found", HttpStatus.NOT_FOUND);
        }
        service.updateCompte(emailClient, nomCompte, operations);
        return new ResponseEntity<>("Compte updated", HttpStatus.OK);
    }
}
