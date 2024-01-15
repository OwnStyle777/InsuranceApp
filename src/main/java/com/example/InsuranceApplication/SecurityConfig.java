package com.example.InsuranceApplication;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        //permit for pages below
                        .requestMatchers("/css/**", "/js/**").permitAll()
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
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080")); // povolené zdroje
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }



}
