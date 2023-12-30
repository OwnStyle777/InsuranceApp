package com.example.InsuranceApplication.client;

import com.example.InsuranceApplication.Insurance;

import java.util.Date;

public class ClientService {
   public Client createClient (){
      Client client = new Client();
      client.setPersonalData(createPersonalData("ss","a","ada",new Date("6.2.1997"),5));
      client.setLoginInfo(createLoginInfo("haha","aaa"));
      client.setInsuranceInfo(createInsuranceInfo("sad",55,"sadd",66));

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

   public LoginInfo createLoginInfo(String nickName, String password){
       LoginInfo loginInfo = new LoginInfo();
       loginInfo.setNickName(nickName);
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
