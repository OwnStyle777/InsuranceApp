package com.example.InsuranceApplication.verification;

import com.example.InsuranceApplication.client.Client;
import com.example.InsuranceApplication.client.ClientDAO;
import org.hibernate.SessionFactory;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

public class AuthTokenGenerator {
    public static String generateAuthToken(long userId){

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 1000 * 60 * 60 * 24); // Expires in 24 hours

        // Generate the token
        String token = userId + "." + expirationDate.getTime();

        return Base64.getEncoder().encodeToString(token.getBytes());
    }

    public static long getUserId(String token) {
        // Decode the token
        byte[] bytes = Base64.getDecoder().decode(token);

        // Split the token
        String[] parts = new String(bytes).split("\\.");

        // Return the user ID
        return Long.parseLong(parts[0]);
    }

    public static Date getExpirationDate(String token) {
        // Decode the token
        byte[] bytes = Base64.getDecoder().decode(token);

        // Split the token
        String[] parts = new String(bytes).split("\\.");

        // Return the expiration date
        return new Date(Long.parseLong(parts[1]));
    }

    public static boolean isValidToken(String token, SessionFactory sessionFactory) {
        // Extract the user ID from the token

        ClientDAO dao = new ClientDAO(sessionFactory);
        // Get the client by ID
        Client client = dao.getClientById( getUserId(token));

        // Check if the client exists
        if (client == null) {
            return false;
        }

        // Check the expiration date
        Date expirationDate = getExpirationDate(token);
        Date currentDate = new Date();
        if (expirationDate.before(currentDate)) {
            return false;
        }

        return true;
    }
}
