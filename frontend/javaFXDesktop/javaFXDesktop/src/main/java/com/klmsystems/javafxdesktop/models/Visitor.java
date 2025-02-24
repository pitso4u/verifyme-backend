package com.klmsystems.javafxdesktop.models;

public class Visitor {
    private String name;
    private int id;
    private String purpose;
    private String entryTime;
    private String department;
    private String remarks;

    public Visitor(String name, int id, String purpose, String entryTime, String department, String remarks) {
        this.name = name;
        this.id = id;
        this.purpose = purpose;
        this.entryTime = entryTime;
        this.department = department;
        this.remarks = remarks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Visitor{" + "name=" + name + ", id=" + id + ", purpose=" + purpose + ", entryTime=" + entryTime + ", department=" + department + ", remarks=" + remarks + '}';
    }

    
} 