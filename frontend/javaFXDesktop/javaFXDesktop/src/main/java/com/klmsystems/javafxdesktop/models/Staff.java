package com.klmsystems.javafxdesktop.models;

import java.sql.Timestamp;
import java.sql.Blob;

public class Staff {
    private int id;
    private String name;
    private String employeeId;
    private Blob faceData; // Storing face embeddings as BLOB
    private Timestamp createdAt;

    public Staff() {
    }

    public Staff(int id, String name, String employeeId, Blob faceData, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.employeeId = employeeId;
        this.faceData = faceData;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Blob getFaceData() {
        return faceData;
    }

    public void setFaceData(Blob faceData) {
        this.faceData = faceData;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}