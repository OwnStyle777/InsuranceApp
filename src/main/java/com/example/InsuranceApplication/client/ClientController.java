package com.example.InsuranceApplication.client;


import com.example.InsuranceApplication.RegistrationForm;
import com.example.InsuranceApplication.insurance.Insurance;
import com.example.InsuranceApplication.insurance.InsuranceDataGeneration;
import com.example.InsuranceApplication.verification.ClientValidator;
import com.example.InsuranceApplication.verification.EmailValidator;
import com.example.InsuranceApplication.verification.PasswordValidator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.text.html.HTML;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

@RestController

public class ClientController implements EmailValidator, PasswordValidator, ClientValidator {
    @Autowired
    ClientService clientService;
    @Autowired
    SessionFactory sessionFactory ;


//    @GetMapping ("/clientInfo")
//    public ResponseEntity<String> createClient() {
//        Client createdClient = clientService.createClient();
//        String clientInfo = "Client created: " + createdClient.getPersonalData().getFirstName(); // Príklad, ako by mohol vyzerať reťazec s informáciami o klientovi
//        return ResponseEntity.ok(clientInfo);
//    }
    @GetMapping("/register")
    public ResponseEntity<byte[]> showRegisterPage() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("static/form.html");
        byte[] html = Files.readAllBytes(Path.of(htmlFile.getURI()));
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html);
    }
    @GetMapping ("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, this is a test endpoint!");
    }


//    @PostMapping ("/register")
//    public ResponseEntity<?> registerUser(@RequestBody Client client){
//        ClientDAO dao = new ClientDAO(sessionFactory);
//        if(validateClient(client)){
//            dao.saveClient(client);
//            return ResponseEntity.ok().body("Registration was successful!");
//        }
//       return ResponseEntity.badRequest().body("Please provide all necessary information to complete registration");
//    }
@PostMapping("/register")
public ResponseEntity<?> registerUser(RegistrationForm registrationForm) {
    // Vytvorenie objektu Client a nastavenie údajov z RegistrationForm

    Client client = clientService.createClient(registrationForm);
    ClientDAO dao = new ClientDAO(sessionFactory);
    if (validateClient(client)) {
        if (dao.saveClient(client)) {
            return ResponseEntity.ok().body("Registration was successful!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the client.");
        }
    } else {
        return ResponseEntity.badRequest().body("Please provide all necessary information to complete registration");
    }
}

    @GetMapping("/login")
    public ResponseEntity<byte[]> showLoginPage() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("static/login.html");
        byte[] html = Files.readAllBytes(Path.of(htmlFile.getURI()));
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser (@RequestParam("email") String email, @RequestParam("password") String password){
        ClientDAO dao = new ClientDAO(sessionFactory);
        if (dao.isEmailInDatabase(email)) {
            Client client = dao.getClientByEmail(email);
            if (client != null && isPasswordValid(password,client.getLoginInfo().getPassword())) {
                RedirectView redirectView = new RedirectView();
                redirectView.setUrl("/clientInfo");
                // Vrátenie úspešnej odpovede
                return ResponseEntity.ok().body(redirectView);
            } else {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password must contain at least one special character, uppercase and lowercase and one digit.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This email address is not registered.");
        }
    }
}
