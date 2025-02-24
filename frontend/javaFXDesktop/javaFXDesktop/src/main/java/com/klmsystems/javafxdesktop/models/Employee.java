package com.klmsystems.javafxdesktop.models;

import java.sql.Timestamp;

public class Employee {
    private int id;
    private String firstname;
    private String surname;
    private String employeeId;
    private String qrCode;
    private Timestamp createdAt;
    private String gender;
    private String IDNumber;
    private String language; // E.g., Afrikaans, English, isiZulu, etc.
    private String address;
    private String emailAddress;
    private String phoneNumber;
    private String department;
    private String position;
    private Timestamp hiredAt;
    private String race; // E.g., African, Coloured, Indian, White, Other
    private String postalCode;
    private int userId;

    // Default constructor
    public Employee() {
    }

    // Parameterized constructor
    public Employee(int id, String firstname, String surname, String employeeId, String qrCode, Timestamp createdAt, String gender, String IDNumber, String language, String address, String emailAddress, String phoneNumber, String department, String position, Timestamp hiredAt, String race, String postalCode, int userId) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.employeeId = employeeId;
        this.qrCode = qrCode;
        this.createdAt = createdAt;
        this.gender = gender;
        this.IDNumber = IDNumber;
        this.language = language;
        this.address = address;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.position = position;
        this.hiredAt = hiredAt;
        this.race = race;
        this.postalCode = postalCode;
        this.userId = userId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Timestamp getHiredAt() {
        return hiredAt;
    }

    public void setHiredAt(Timestamp hiredAt) {
        this.hiredAt = hiredAt;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Additional method to print employee information
    public void printEmployeeInfo() {
        System.out.println("Employee ID: " + id);
        System.out.println("First Name: " + firstname);
        System.out.println("Surname: " + surname);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("QR Code: " + qrCode);
        System.out.println("Created At: " + createdAt);
        System.out.println("Gender: " + gender);
        System.out.println("ID Number: " + IDNumber);
        System.out.println("Language: " + language);
        System.out.println("Address: " + address);
        System.out.println("Email Address: " + emailAddress);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Department: " + department);
        System.out.println("Position: " + position);
        System.out.println("Hired At: " + hiredAt);
        System.out.println("Race: " + race);
        System.out.println("Postal Code: " + postalCode);
    }
} 