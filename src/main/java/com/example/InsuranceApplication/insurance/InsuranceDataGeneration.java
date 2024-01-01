package com.example.InsuranceApplication.insurance;

import java.util.Random;

public class InsuranceDataGeneration {
    public static int generateInsuranceNumber(String insuranceCompany) {
        switch (insuranceCompany) {
            case "DOVERA":
                return 100;
            case "ALLIANZ":
                return 150;
            // Ďalšie poistovne...
            default:
                return 0; // Nedefinovaná poistovňa
        }
    }

    public static String generateIdentificationNumber() {
        StringBuilder identificationNumber = new StringBuilder();
        Random random = new Random();
        // Generovanie náhodných písmen
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 4; i++) {
            int randomIndex = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(randomIndex);
            identificationNumber.append(randomChar);
        }

        // Generovanie náhodného identifikačného čísla

        for (int i = 0; i < 6; i++) {
            int randomDigit = random.nextInt(10); // Náhodné číslice
            identificationNumber.append(randomDigit);
        }
        
        return identificationNumber.toString();
    }
}
