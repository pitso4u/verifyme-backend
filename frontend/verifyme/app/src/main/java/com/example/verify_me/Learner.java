package com.example.verify_me;

import com.google.gson.annotations.SerializedName;

public class Learner {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("course")
    private String course;

    @SerializedName("enrollment_date")
    private String enrollmentDate;

    public Learner() {
    }

    public Learner(int id, String name, String email, String course, String enrollmentDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
} 