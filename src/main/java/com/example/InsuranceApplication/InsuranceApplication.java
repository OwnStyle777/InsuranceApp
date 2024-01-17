package com.example.InsuranceApplication;

import com.example.InsuranceApplication.forms.RegistrationForm;
import com.example.InsuranceApplication.client.Client;
import com.example.InsuranceApplication.client.ClientService;
import com.example.InsuranceApplication.verification.ClientValidator;
import com.example.InsuranceApplication.verification.PersonalDataValidation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;


@SpringBootApplication
public class InsuranceApplication  implements ClientValidator  {


	public  static void main(String[] args)  {
		SpringApplication.run(InsuranceApplication.class, args);
	}

}
