package com.example.InsuranceApplication.client;


import com.example.InsuranceApplication.verification.EmailValidator;
import com.example.InsuranceApplication.verification.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController

public class ClientController implements EmailValidator, PasswordValidator {
    @Autowired
  ClientService clientService;
    @GetMapping ("/clientInfo")
    public ResponseEntity<String> createClient() {
        Client createdClient = clientService.createClient();
        String clientInfo = "Client created: " + createdClient.getPersonalData().getFirstName(); // Príklad, ako by mohol vyzerať reťazec s informáciami o klientovi
        return ResponseEntity.ok(clientInfo);
    }
    @GetMapping ("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, this is a test endpoint!");
    }


    @PostMapping ("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginInfo loginInfo) {

        String userEmail = loginInfo.getEmail();
        String userPassword = loginInfo.getPassword();
        if (isEmailValid(userEmail)) {

            if (validatePassword(userPassword)) {
                RedirectView redirectView = new RedirectView();
                redirectView.setUrl("/client");
                // Vrátenie úspešnej odpovede
                return ResponseEntity.ok().body("Login was successful!");
            } else {

                return ResponseEntity.badRequest().body("Password must contain at least one special character, uppercase and lowercase and one digit");
            }
        } else {

            return ResponseEntity.badRequest().body("Wrong format of email address.");
        }
    }

    @PostMapping ("/register")
    public ResponseEntity<?> registerUser(@RequestBody Client client){
        if(!(client == null)){
            return ResponseEntity.ok().body("Registration was successful!");
        }
       return ResponseEntity.badRequest().body("Please provide all necessary information to complete registration");
    }

}
