package com.example.InsuranceApplication.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class HtmlViewController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "form";
    }
    @GetMapping("/clientInfo")
    public String showClientPage() {
       return "client";
    }
    @GetMapping ("/hello" )
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, this is a test endpoint!");
    }

}
