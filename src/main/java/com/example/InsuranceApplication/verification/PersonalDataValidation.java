package com.example.InsuranceApplication.verification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface PersonalDataValidation {
    default boolean isNameValid (String name){
        String nameRegex = "^[A-ZÁÍÉÝŠČŽĆĐĹĽŤÚáíéýščžćđĺľťú][A-Za-zÁÍÉÝŠČŽĆĐĹĽŤÚáíéýščžćđĺľťú\\s'-]+";
        return name.matches(nameRegex) && name.length() >= 2;
    }
    default boolean isNumberValid (String phoneNumber){
            String phoneRegex = "^(0|\\+421)\\d{9}$";
            return phoneNumber.matches(phoneRegex);
    }
    default boolean isDateValid(String date) {
        String dateRegex = "^(0[1-9]|1[0-2])[.-](0[1-9]|1[0-9]|2[0-9]|3[01])[.-](19\\d{2}|20\\d{2})$";
        return date.matches(dateRegex);
    }
}
