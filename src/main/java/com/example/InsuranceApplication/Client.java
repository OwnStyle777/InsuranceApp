package com.example.InsuranceApplication;

public class Client {
    private String firstName;
    private String secondName;
    private String email;
    private int age;
    private int number;
    private String nickName;
    private String password;

    public Client(String firstName, String secondName, String email, int age, int number, String nickName, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.age = age;
        this.number = number;
        this.nickName = nickName;
        this.password = password;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
