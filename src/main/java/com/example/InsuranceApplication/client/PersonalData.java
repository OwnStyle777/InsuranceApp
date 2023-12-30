package com.example.InsuranceApplication.client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;
@Entity
@Table (name = "personalData")
public class PersonalData {
    @Column (name = "firstName")
    private String firstName;
    @Column (name = "secondName")
    private String secondName;
    @Column (name = "email")
    private String email;
    @Column (name = "birthDate")
    private Date birthDate;
    @Column (name = "number")
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
