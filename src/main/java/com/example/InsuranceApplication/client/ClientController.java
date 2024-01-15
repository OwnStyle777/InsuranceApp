package com.example.InsuranceApplication.client;


import com.example.InsuranceApplication.forms.LoginForm;
import com.example.InsuranceApplication.forms.RegistrationForm;
import com.example.InsuranceApplication.verification.ClientValidator;
import com.example.InsuranceApplication.verification.EmailValidator;
import com.example.InsuranceApplication.verification.PasswordValidator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin(origins = "http://localhost:8080")

public class ClientController implements EmailValidator, PasswordValidator, ClientValidator {
    @Autowired
    ClientService clientService;
    @Autowired
    SessionFactory sessionFactory ;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser ( @RequestParam String email,
                                         @RequestParam String password) {

        ClientDAO dao = new ClientDAO(sessionFactory);
        System.out.println("email adress : " + email);
        System.out.println("password : " + password);
        if (dao.isEmailInDatabase(email)) {
            Client client = dao.getClientByEmail(email);
            if (client != null && isPasswordLengthOK(password)) {
                RedirectView redirectView = new RedirectView();
                redirectView.setUrl("/clientInfo");

                return ResponseEntity.ok().header("Location", "/clientInfo").build();
            } else {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password must contain at least one special character, uppercase and lowercase and one digit.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This email address is not registered.");
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

        Client client = clientService.createClient(form);

        // Validate form data and perform any necessary processing
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

}
