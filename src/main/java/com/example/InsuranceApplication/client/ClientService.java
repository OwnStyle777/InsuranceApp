package com.example.InsuranceApplication.client;

import com.example.InsuranceApplication.Insurance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ClientService {


   public Client createClient (){
      Client client = new Client();
          SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
       Date birthDate = null;
       try {
           birthDate = sdf.parse("06.02.1997");
       } catch (ParseException e) {
           System.out.println("parse ERROR");
       }
      client.setPersonalData(createPersonalData("ss","a","ada",birthDate,5));
      client.setLoginInfo(createLoginInfo("haha","aaa"));
      client.setInsuranceInfo(createInsuranceInfo("sad",55,"said",66));

      return client;
   }

   public PersonalData createPersonalData(String firstName, String secondName, String email, Date birthDate, int number){
       PersonalData personalData = new PersonalData();
       personalData.setFirstName(firstName);
       personalData.setSecondName(secondName);
       personalData.setEmail(email);
       personalData.setBirthDate(birthDate);
       personalData.setNumber(number);
       return personalData;
   }

   public LoginInfo createLoginInfo(String email, String password){
       LoginInfo loginInfo = new LoginInfo();
       loginInfo.setEmail(email);
       loginInfo.setPassword(password);
       return loginInfo;
   }

   public Insurance createInsuranceInfo(String identificationNumberOfInsured, int insuranceNumber, String nameOfInsuranceCompany, int birthNumber){
       Insurance insuranceInfo = new Insurance();

       insuranceInfo.setIdentificationNumberOfInsured(identificationNumberOfInsured);
       insuranceInfo.setInsuranceNumber(insuranceNumber);
       insuranceInfo.setNameOfInsuranceCompany(nameOfInsuranceCompany);
       insuranceInfo.setBirthNumber(birthNumber);

       return insuranceInfo;

   }
}
