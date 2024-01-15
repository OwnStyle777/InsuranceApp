package com.example.InsuranceApplication.verification;

import com.example.InsuranceApplication.client.Client;
import com.example.InsuranceApplication.client.LoginInfo;
import com.example.InsuranceApplication.client.PersonalData;
import com.example.InsuranceApplication.insurance.Insurance;

import java.util.Date;

public interface ClientValidator extends PersonalDataValidation, InsuranceDataValidation, PasswordValidator, EmailValidator{

    default  boolean validateClient(Client client){

        System.out.println("Validating client: " + client.toString());
        Insurance insurance = client.getInsuranceInfo();
        PersonalData personalData = client.getPersonalData();
        LoginInfo loginInfo = client.getLoginInfo();
        return validateInsuranceData(insurance) && validatePersonalData(personalData) && validateLoginInfo(loginInfo);
    }
    default boolean validatePersonalData (PersonalData personalData){
        String firstName = personalData.getFirstName();
        String secondName = personalData.getSecondName();
        String birthDate = personalData.getBirthDate();
        String number = personalData.getNumber();

        return isDateValid(birthDate) && isNumberValid(number) && isNameValid(firstName) && isNameValid(secondName);
    }
    default boolean validateInsuranceData (Insurance insurance){
        String birthNumber = insurance.getBirthNumber();
        String insuranceCompany = insurance.getNameOfInsuranceCompany();

        return isBirthNumberValid(birthNumber) && isValidInsuranceCompany(insuranceCompany);
    }
    default boolean validateLoginInfo (LoginInfo loginInfo){
        String email = loginInfo.getEmail();
        String password = loginInfo.getPassword();

        return validatePassword(password) && isEmailValid(email);
    }
}
