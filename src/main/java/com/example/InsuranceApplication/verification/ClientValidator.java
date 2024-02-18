package com.example.InsuranceApplication.verification;

import com.example.InsuranceApplication.client.Client;
import com.example.InsuranceApplication.client.LoginInfo;
import com.example.InsuranceApplication.client.PersonalData;
import com.example.InsuranceApplication.forms.RegistrationForm;
import com.example.InsuranceApplication.forms.UpdateForm;
import com.example.InsuranceApplication.insurance.Insurance;

import java.util.Date;

public interface ClientValidator extends PersonalDataValidation, InsuranceDataValidation, PasswordValidator, EmailValidator{

    default  boolean validateClient(Client client, RegistrationForm form){

        System.out.println("Validating client: " + client.toString());
        Insurance insurance = client.getInsuranceInfo();
        PersonalData personalData = client.getPersonalData();
        LoginInfo loginInfo = client.getLoginInfo();
        return validateInsuranceData(insurance) && validatePersonalData(personalData) && validateLoginInfo(loginInfo, form);
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
    default boolean validateLoginInfo (LoginInfo loginInfo, RegistrationForm form){
        String email = loginInfo.getEmail();
        String password = form.getPassword();

        return validatePassword(password) && isEmailValid(email);
    }

    default boolean validateUpdatedData(UpdateForm updatedForm){
        String firstName = updatedForm.getFirstName();
        String email = updatedForm.getEmail();
        String number = updatedForm.getPhoneNumber();
        String insuranceCompany = updatedForm.getInsuranceCompany();

        return isEmailValid(email) && isNameValid(firstName) && isNumberValid(number) && isValidInsuranceCompany(insuranceCompany);
    }
}
