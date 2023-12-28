package com.example.InsuranceApplication.client;

import com.example.InsuranceApplication.Insurance;

import java.util.Date;

public class Client {
    private PersonalData personalData;
    private LoginInfo loginInfo;
    private Insurance insuranceInfo;

    public Client(PersonalData personalData, LoginInfo loginInfo, Insurance insuranceInfo) {
        this.personalData = personalData;
        this.loginInfo = loginInfo;

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
