package com.example.InsuranceApplication.insurance;

import java.util.Random;

public class InsuranceDataGeneration {
    public int generateInsuranceNumber(String insuranceCompany) {
        return switch (insuranceCompany) {
            case "DOVERA" -> 100;
            case "ALLIANZ" -> 150;
            case "UNIQA" -> 200;
            case "UNION" -> 250;
            case "KOOPERATIVA" -> 300;
            case "KOMUNALNA" -> 350;
            case "CSOB" -> 400;
            case "WUSTENROT" -> 450;
            case "METLIFE" -> 500;
            default -> 0; // Nedefinovaná poistovňa
        };
    }

    public String generateIdentificationNumber() {
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
