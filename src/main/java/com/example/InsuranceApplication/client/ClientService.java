package com.example.InsuranceApplication.client;

import com.example.InsuranceApplication.forms.RegistrationForm;
import com.example.InsuranceApplication.forms.UpdateForm;
import com.example.InsuranceApplication.insurance.Insurance;
import com.example.InsuranceApplication.insurance.InsuranceDataGeneration;
import com.example.InsuranceApplication.verification.AuthTokenGenerator;
import com.example.InsuranceApplication.verification.PasswordValidator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements PasswordValidator {

    public RegistrationForm createForm(String firstName, String lastName, String email, String password, String birthDate, String birthNumber, String phoneNumber, String insuranceCompany) {

        RegistrationForm form = new RegistrationForm();
        form.setFirstName(firstName);
        form.setLastName(lastName);
        form.setEmail(email);
        form.setPassword(password);
        form.setBirthDate(birthDate);
        form.setPhoneNumber(phoneNumber);
        form.setBirthNumber(birthNumber);
        form.setInsuranceCompany(insuranceCompany);
        return form;
    }

    public Client createClient(RegistrationForm registrationForm) {
        Client client = new Client();
        client.setPersonalData(createPersonalData(registrationForm));
        client.setLoginInfo(createLoginInfo(registrationForm));
        client.setInsuranceInfo(createInsuranceInfo(registrationForm));

        return client;

    }

    public PersonalData createPersonalData(RegistrationForm registrationForm) {

        PersonalData personalData = new PersonalData();
        personalData.setFirstName(registrationForm.getFirstName());
        personalData.setSecondName(registrationForm.getLastName());
        personalData.setBirthDate(registrationForm.getBirthDate());
        personalData.setNumber(registrationForm.getPhoneNumber());
        return personalData;
    }

    public LoginInfo createLoginInfo(RegistrationForm registrationForm) {
        LoginInfo loginInfo = new LoginInfo();
        String hashedPassword = getHashedPassword(registrationForm.getPassword());
        loginInfo.setEmail(registrationForm.getEmail());
        loginInfo.setPassword(hashedPassword);
        return loginInfo;
    }

    public Insurance createInsuranceInfo(RegistrationForm registrationForm) {
        Insurance insuranceInfo = new Insurance();
        InsuranceDataGeneration dataGeneration = new InsuranceDataGeneration();

        insuranceInfo.setIdentificationNumberOfInsured(dataGeneration.generateIdentificationNumber());
        insuranceInfo.setInsuranceNumber(dataGeneration.generateInsuranceNumber(registrationForm.getInsuranceCompany()));
        insuranceInfo.setNameOfInsuranceCompany(registrationForm.getInsuranceCompany());
        insuranceInfo.setBirthNumber(registrationForm.getBirthNumber());

        return insuranceInfo;

    }

    public Client getClientFromToken(HttpServletRequest request, SessionFactory sessionFactory) {

        // Get the auth token from the cookie
        Cookie[] cookies = request.getCookies();
        String authToken = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authToken")) {
                    authToken = cookie.getValue();
                    break;
                }
            }
        }

        // If the token is not found, return null
        if (authToken == null) {
            return null;
        }

        // Decode the token

        long userId = AuthTokenGenerator.getUserId(authToken);

        // Get the client from the database
        ClientDAO dao = new ClientDAO(sessionFactory);
        return dao.getClientById(userId);
    }

    public UpdateForm createUpdateForm(String name, String email, String phoneNumber, String insuranceCompany) {
        UpdateForm updateForm = new UpdateForm();
        updateForm.setFirstName(name);
        updateForm.setEmail(email);
        updateForm.setPhoneNumber(phoneNumber);
        updateForm.setInsuranceCompany(insuranceCompany);

        return updateForm;
    }

    public void setPersonalData(String firstName, String phoneNumber, PersonalData personalData) {
        personalData.setFirstName(firstName);
        personalData.setNumber(phoneNumber);
    }

    public void updateClientData(String firstName, String phoneNumber, String email, String insuranceCompany, Client client) {

        PersonalData personalData = client.getPersonalData();
        LoginInfo loginInfo = client.getLoginInfo();
        Insurance insurance = client.getInsuranceInfo();
        setPersonalData(firstName, phoneNumber, personalData);
        loginInfo.setEmail(email);
        insurance.setNameOfInsuranceCompany(insuranceCompany);

    }

}
