package com.example.InsuranceApplication.verification;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

public class AuthTokenGenerator {
    public static String generateAuthToken(int userId){

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 1000 * 60 * 60 * 24); // Expires in 24 hours

        // Generate the token
        String token = userId + "." + expirationDate.getTime();

        return Base64.getEncoder().encodeToString(token.getBytes());
    }

    public static int getUserId(String token) {
        // Decode the token
        byte[] bytes = Base64.getDecoder().decode(token);

        // Split the token
        String[] parts = new String(bytes).split("\\.");

        // Return the user ID
        return Integer.parseInt(parts[0]);
    }

    public static Date getExpirationDate(String token) {
        // Decode the token
        byte[] bytes = Base64.getDecoder().decode(token);

        // Split the token
        String[] parts = new String(bytes).split("\\.");

        // Return the expiration date
        return new Date(Long.parseLong(parts[1]));
    }
}
