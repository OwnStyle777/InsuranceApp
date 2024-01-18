package com.example.InsuranceApplication.verification;

import java.security.SecureRandom;
import java.util.Base64;

public class AuthTokenGenerator {
    public static String generateAuthToken(){
        byte[] randomBytes = new byte[32]; // Adjust the length based on your requirements
        new SecureRandom().nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }
}
