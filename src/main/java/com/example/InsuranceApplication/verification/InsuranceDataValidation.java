package com.example.InsuranceApplication.verification;

public interface InsuranceDataValidation {
    default boolean isBirthNumberValid(int birthNumber){
        return String.valueOf(birthNumber).length() == 10;
    }

}
