package com.example.InsuranceApplication.client;

import com.example.InsuranceApplication.Insurance;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table (name = "clients")
public class Client {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_data_id", referencedColumnName = "id")
    private PersonalData personalData;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_info_id", referencedColumnName = "id")
    private LoginInfo loginInfo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insurance_info_id", referencedColumnName = "id")
    private Insurance insuranceInfo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public Insurance getInsuranceInfo() {
        return insuranceInfo;
    }

    public void setInsuranceInfo(Insurance insuranceInfo) {
        this.insuranceInfo = insuranceInfo;
    }
}
