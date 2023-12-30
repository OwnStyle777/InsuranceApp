package com.example.InsuranceApplication.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class ClientController {
//    @Autowired
//    private ClientService clientService;
//    @PostMapping("/create")
//    public ResponseEntity<Client> createClient(@RequestBody Client client) {
//
//
//        Client createdClient = clientService.createClient();
//        return ResponseEntity.ok(createdClient);
//
//
//    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, this is a test endpoint!");
    }

}
