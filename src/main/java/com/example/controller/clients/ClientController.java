package com.example.controller.clients;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.business.clients.modele.Client;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

        @PostMapping("/clients")
        public ResponseEntity<String> addClient(@RequestBody Client client) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping("/clients/{email}")
        public ResponseEntity<String> getClient(@PathVariable String email) {
            return new ResponseEntity<>("Client found", HttpStatus.OK);
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
