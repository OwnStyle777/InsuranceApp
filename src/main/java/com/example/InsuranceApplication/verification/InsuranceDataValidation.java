package com.example.InsuranceApplication.verification;

import com.example.InsuranceApplication.insurance.InsuranceCompanies;

public interface InsuranceDataValidation {
    default boolean isBirthNumberValid(String birthNumber){
        return birthNumber.length() == 10;
    }

    default boolean isValidInsuranceCompany(String company) {
        for (InsuranceCompanies insuranceCompany : InsuranceCompanies.values()) {
            if (insuranceCompany.name().equalsIgnoreCase(company)) {
                return true;
            }
        }
        return false;
    }

}
