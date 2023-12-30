package com.example.InsuranceApplication.client;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table (name = "personalData")
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private Date birthDate;
    private int number;

    public PersonalData(String firstName, String secondName, String email, Date birthDate, int number) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.birthDate = birthDate;
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
