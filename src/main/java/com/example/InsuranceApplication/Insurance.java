package com.example.InsuranceApplication;

public class Insurance {
    private String identificationNumberOfInsured;
    private int insuranceNumber;
    private String nameOfInsuranceCompany;
    private int birthNumber;

    public Insurance(String identificationNumberOfInsured, int insuranceNumber, String nameOfInsuranceCompany, int birthNumber) {
        this.identificationNumberOfInsured = identificationNumberOfInsured;
        this.insuranceNumber = insuranceNumber;
        this.nameOfInsuranceCompany = nameOfInsuranceCompany;
        this.birthNumber = birthNumber;
    }

    public String getIdentificationNumberOfInsured() {
        return identificationNumberOfInsured;
    }

    public void setIdentificationNumberOfInsured(String identificationNumberOfInsured) {
        this.identificationNumberOfInsured = identificationNumberOfInsured;
    }

    public int getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(int insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getNameOfInsuranceCompany() {
        return nameOfInsuranceCompany;
    }

    public void setNameOfInsuranceCompany(String nameOfInsuranceCompany) {
        this.nameOfInsuranceCompany = nameOfInsuranceCompany;
    }

    public int getBirthNumber() {
        return birthNumber;
    }

    public void setBirthNumber(int birthNumber) {
        this.birthNumber = birthNumber;
    }
}
