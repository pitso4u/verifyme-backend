package com.klmsystems.javafxdesktop.models;

public class AttendanceRequest {
    private String qrCodeData;

    public AttendanceRequest(String qrCodeData) {
        this.qrCodeData = qrCodeData;
    }

    public String getQrCodeData() { return qrCodeData; }
}

