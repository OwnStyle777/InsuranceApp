package com.example.InsuranceApplication.verification;

import com.example.InsuranceApplication.client.LoginInfo;
import com.example.InsuranceApplication.client.PersonalData;
import com.example.InsuranceApplication.insurance.Insurance;

import java.util.Date;

public interface ClientValidator extends PersonalDataValidation, InsuranceDataValidation, PasswordValidator, EmailValidator{

    default boolean validateClient (PersonalData personalData, Insurance insurance, LoginInfo loginInfo){
        return validateInsuranceData(insurance) && validatePersonalData(personalData) && validateLoginInfo(loginInfo);
    }
    default boolean validatePersonalData (PersonalData personalData){
        String firstName = personalData.getFirstName();
        String secondName = personalData.getSecondName();
        Date birthDate = personalData.getBirthDate();
        String number = personalData.getNumber();

        return isDateValid(String.valueOf(birthDate)) && isNumberValid(number) && isNameValid(firstName) && isNameValid(secondName);
    }
    default boolean validateInsuranceData (Insurance insurance){
        int birthNumber = insurance.getBirthNumber();
        String insuranceCompany = insurance.getNameOfInsuranceCompany();

        return isBirthNumberValid(birthNumber) && isValidInsuranceCompany(insuranceCompany);
    }
    default boolean validateLoginInfo (LoginInfo loginInfo){
        String email = loginInfo.getEmail();
        String password = loginInfo.getPassword();

        return validatePassword(password) && isEmailValid(email);
    }
}
