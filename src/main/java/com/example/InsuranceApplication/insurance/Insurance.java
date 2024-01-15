package com.example.InsuranceApplication.insurance;

import jakarta.persistence.*;

@Entity
@Table(name = "insurance")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identificationNumberOfInsured;
    private int insuranceNumber;
    private String nameOfInsuranceCompany;
    private String birthNumber;


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

    public String getBirthNumber() {
        return birthNumber;
    }

    public void setBirthNumber(String birthNumber) {
        this.birthNumber = birthNumber;
    }
}
