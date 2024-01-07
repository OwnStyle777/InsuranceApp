package com.example.InsuranceApplication.client;

import com.example.InsuranceApplication.RegistrationForm;
import com.example.InsuranceApplication.insurance.Insurance;
import com.example.InsuranceApplication.insurance.InsuranceDataGeneration;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ClientService {
   public Client createClient (RegistrationForm registrationForm){
       Client client = new Client();
       client.setPersonalData(createPersonalData(registrationForm));
       client.setLoginInfo(createLoginInfo(registrationForm));
       client.setInsuranceInfo(createInsuranceInfo(registrationForm));

       return client;

   }

   public PersonalData createPersonalData(RegistrationForm registrationForm){

       PersonalData personalData = new PersonalData();
       personalData.setFirstName(registrationForm.getFirstName());
       personalData.setSecondName(registrationForm.getLastName());
       personalData.setBirthDate(new Date(registrationForm.getBirthDate()));
       personalData.setNumber(registrationForm.getPhoneNumber());
       return personalData;
   }

   public LoginInfo createLoginInfo(RegistrationForm registrationForm){
       LoginInfo loginInfo = new LoginInfo();
       loginInfo.setEmail(registrationForm.getEmail());
       loginInfo.setPassword(registrationForm.getPassword());
       return loginInfo;
   }

   public Insurance createInsuranceInfo(RegistrationForm registrationForm){
       Insurance insuranceInfo = new Insurance();
       InsuranceDataGeneration dataGeneration  = new InsuranceDataGeneration();

       insuranceInfo.setIdentificationNumberOfInsured(dataGeneration.generateIdentificationNumber());
       insuranceInfo.setInsuranceNumber(dataGeneration.generateInsuranceNumber(registrationForm.getInsuranceCompany()));
       insuranceInfo.setNameOfInsuranceCompany(registrationForm.getInsuranceCompany());
       insuranceInfo.setBirthNumber(Integer.parseInt(registrationForm.getBirthNumber()));

       return insuranceInfo;

   }
}
