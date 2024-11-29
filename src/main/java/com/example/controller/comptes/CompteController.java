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

    @PutMapping("/{emailClient}/{nomCompte}")
    public ResponseEntity<String> addCompte(@PathVariable String emailClient, @RequestBody String nomCompte) {
        ComptesService service = new ComptesService();
        if (service.compteExists(emailClient, nomCompte)) {
            return new ResponseEntity<>("Compte already exists", HttpStatus.CONFLICT);
        }
        service.addCompte(emailClient, nomCompte);
        return new ResponseEntity<>("Compte added", HttpStatus.CREATED);
    }
}
