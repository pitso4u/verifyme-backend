package com.example.verify_me;

import com.google.gson.annotations.SerializedName;

public class Visitor {
    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("purpose")
    private String purpose;

    @SerializedName("visit_date")
    private String visitDate;

    @SerializedName("status")
    private String status;

    public Visitor() {
    }

    public Visitor(int id, String username, String email, String purpose, String visitDate, String status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.purpose = purpose;
        this.visitDate = visitDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
} 