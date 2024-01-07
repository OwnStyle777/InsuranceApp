package com.example.InsuranceApplication;

import com.example.InsuranceApplication.client.Client;
import com.example.InsuranceApplication.client.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.sql.PreparedStatement;

@SpringBootApplication
public class InsuranceApplication {

	public static void main(String[] args) {

		ClientService clientService = new ClientService();

//		Client peter = clientService.createClient();
//		System.out.println(peter + "  here is created client");
		SpringApplication.run(InsuranceApplication.class, args);
	}

}
