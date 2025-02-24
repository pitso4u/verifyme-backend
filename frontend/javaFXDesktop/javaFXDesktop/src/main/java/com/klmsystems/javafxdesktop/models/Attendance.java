package com.klmsystems.javafxdesktop.models;

import java.sql.Timestamp;

public class Attendance {
    private int id;
    private int userId;
    private String role; // 'learner' or 'staff'
    private String status; // 'present' or 'absent'
    private Timestamp timestamp;

    public Attendance() {
    }

    public Attendance(int id, int userId, String role, String status, Timestamp timestamp) {
        this.id = id;
        this.userId = userId;
        this.role = role;
        this.status = status;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}