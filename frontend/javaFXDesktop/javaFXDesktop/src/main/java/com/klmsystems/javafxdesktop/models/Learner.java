package com.klmsystems.javafxdesktop.models;

import java.sql.Timestamp;

public class Learner {
    private int id;
    private String firstname;
    private String surname;
    private String studentId;
    private String qrCode;
    private Timestamp createdAt;
    private String gender;
    private String IDNumber;
    private String language; // E.g., Afrikaans, English, isiZulu, etc.
    private String address;
    private String emailAddress;
    private String phoneNumber;

    // Default constructor
    public Learner() {
    }

    // Parameterized constructor
    public Learner(int id, String firstname, String surname, String studentId, String qrCode, Timestamp createdAt, String gender, String IDNumber, String language, String address, String emailAddress, String phoneNumber) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.studentId = studentId;
        this.qrCode = qrCode;
        this.createdAt = createdAt;
        this.gender = gender;
        this.IDNumber = IDNumber;
        this.language = language;
        this.address = address;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    // Additional method to print learner information
    public void printLearnerInfo() {
        System.out.println("Learner ID: " + id);
        System.out.println("First Name: " + firstname);
        System.out.println("Surname: " + surname);
        System.out.println("Student ID: " + studentId);
        System.out.println("QR Code: " + qrCode);
        System.out.println("Created At: " + createdAt);
        System.out.println("Gender: " + gender);
        System.out.println("ID Number: " + IDNumber);
        System.out.println("Language: " + language);
        System.out.println("Address: " + address);
        System.out.println("Email Address: " + emailAddress);
        System.out.println("Phone Number: " + phoneNumber);
    }
}