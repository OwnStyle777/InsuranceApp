package com.example.InsuranceApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class ClientInsuranceAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private DataSource dataSource;

	@Test
	public void testConnection() {
		try {
			dataSource.getConnection();
		} catch (SQLException e) {

			System.out.println("Connection to database couldn't established");
		}
	}

}
