package com.example.InsuranceApplication;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        //permit for pages below
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/clientInfo/**").permitAll()
                        .requestMatchers("/hello/**")

                )
                .securityMatchers((matchers) -> matchers
                        .requestMatchers("/css/**", "/js/**")                  //allow display css and js
                )
                .formLogin(formLogin -> formLogin        //default page
                        .loginPage("/login")
                        .permitAll()

                )
                .rememberMe(Customizer.withDefaults());

        return http.build();
    }


}
