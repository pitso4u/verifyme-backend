package com.example.verify_me;

public class Attendance {
    private int attendanceId;
    private int studentId;
    private String date;
    private String method;
    private String trainingPhoto;
    private String timestamp;

    // Getters and Setters
    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTrainingPhoto() {
        return trainingPhoto;
    }

    public void setTrainingPhoto(String trainingPhoto) {
        this.trainingPhoto = trainingPhoto;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
} 