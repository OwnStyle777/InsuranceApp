package com.example.InsuranceApplication.verification;

public interface EmailValidator {

  default boolean isEmailValid (String email){
      String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
      if(!email.matches(regex)){
          System.out.println("Wrong format of email address.");
          return false;
      }
      return true;
  }
}
