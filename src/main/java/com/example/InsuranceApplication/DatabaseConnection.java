package com.example.InsuranceApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;



@Configuration
public class DatabaseConnection {

    @Value("${spring.datasource.url}")
    private String dbUrl ;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword ;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;

    }
    
//            statement = connection.createStatement();
//
//          statement.execute("CREATE TABLE IF NOT EXISTS meals (" +
//             "category varchar NOT NULL," +
//                  "meal varchar NOT NULL," +
//                "meal_id INTEGER NOT NULL PRIMARY KEY" +
//                  ")");


}
