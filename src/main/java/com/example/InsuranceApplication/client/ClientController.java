package com.example.InsuranceApplication.client;


import com.example.InsuranceApplication.forms.RegistrationForm;
import com.example.InsuranceApplication.verification.AuthTokenGenerator;
import com.example.InsuranceApplication.verification.ClientValidator;
import com.example.InsuranceApplication.verification.EmailValidator;
import com.example.InsuranceApplication.verification.PasswordValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:8080")

public class ClientController implements EmailValidator, PasswordValidator, ClientValidator {
    @Autowired
    ClientService clientService;
    @Autowired
    SessionFactory sessionFactory ;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser (@RequestParam String email, @RequestParam String password, HttpServletResponse response) throws JsonProcessingException {

        ClientDAO dao = new ClientDAO(sessionFactory);
        System.out.println("email adress : " + email);
        System.out.println("password : " + password);
        if (dao.isEmailInDatabase(email)) {
            Client client = dao.getClientByEmail(email);
            String passwordInDB = client.getLoginInfo().getPassword();
            if (isPasswordValid(password,passwordInDB)) {
                // Generate and set authentication token
                String authToken = AuthTokenGenerator.generateAuthToken(client.getId());
                response.addCookie(new Cookie("authToken", authToken));

                //put id of user in response map and
                Map<String, Object> responseMap = new HashMap<>();
                
                responseMap.put("userId", client.getId());
                System.out.println(authToken);

                    return new ResponseEntity<>( responseMap, HttpStatus.OK);
            } else {
                return ResponseEntity.status(403).body(null);
            }
        } else {
            return ResponseEntity.badRequest().body("This email is not registered");
        }
    }
    @GetMapping("/users")
    public ResponseEntity<?> getUserData(@RequestHeader("Authorization") String authToken) {
        System.out.println(authToken);

        String pureToken = authToken.substring(7);   //without Bearer
        System.out.println(pureToken);

        if (AuthTokenGenerator.isValidToken(pureToken, sessionFactory)) {
            // Validate the authentication token
            long userId = AuthTokenGenerator.getUserId(pureToken);
            ClientDAO dao = new ClientDAO(sessionFactory);
            Client client = dao.getClientById( userId);
            // Retrieve the user data from the database


            // If the user data is found, return it to the frontend
            if (client != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String jsonResponse = objectMapper.writeValueAsString(client);

                    // Set the HTTP headers
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    // Create a ResponseEntity with the response and status code OK (200)
                    return new ResponseEntity<>(jsonResponse, headers, HttpStatus.OK);
                } catch (JsonProcessingException e) {
                    // Error processing JSON
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing JSON");
                }
            } else {
                // User data not found
                return ResponseEntity.badRequest().body("User data not found");
            }
        } else {
            // Invalid authentication token
            return ResponseEntity.badRequest().body("Invalid authentication token");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("birthDate") String birthDate,
            @RequestParam("birthNumber") String birthNumber,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("insurance") String insurance) {

        // Create a user object based on the extracted data
        RegistrationForm form = clientService.createForm(firstName, lastName, email, password, birthDate, birthNumber, phoneNumber, insurance);

        System.out.println(form.toString());
        try (Session session = sessionFactory.openSession()) {

            ClientDAO dao = new ClientDAO(sessionFactory);

            Client client = clientService.createClient(form);

            // Validate form data and perform any necessary processing
            System.out.println("User is not in database ?  :" + !dao.isEmailInDatabase(client.getLoginInfo().getEmail()));
            if (!dao.isEmailInDatabase(client.getLoginInfo().getEmail())) {

                System.out.println("Validation of client: " + validateClient(client, form));

                if (validateClient(client, form)) {
                    dao.saveClient(client, session);
                    return ResponseEntity.ok().body("Registration was successful!");
                } else {
                    return ResponseEntity.status(403).body("Please provide all necessary information to complete registration");
                }
            } else {
                return ResponseEntity.badRequest().body("This email is already registered.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the client.");
        }
}
    @GetMapping("/checkEmail")
    @ResponseBody
    public boolean checkEmail(@RequestParam String email) {
        System.out.println(email);

        ClientDAO dao = new ClientDAO(sessionFactory);
        return dao.isEmailInDatabase(email);
    }

}
