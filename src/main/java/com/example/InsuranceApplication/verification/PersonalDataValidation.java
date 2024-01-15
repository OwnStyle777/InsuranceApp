package com.example.InsuranceApplication.verification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface PersonalDataValidation {
    default boolean isNameValid (String name){
        String nameRegex = "[A-Z][a-zA-z ']+";
        return name.matches(nameRegex) && name.length() >= 2;
    }
    default boolean isNumberValid (String phoneNumber){
            String phoneRegex = "^(0|\\+421)\\d{9}$";
            return phoneNumber.matches(phoneRegex);
    }
    default boolean isDateValid(String date) {
        return true;
    }
}
