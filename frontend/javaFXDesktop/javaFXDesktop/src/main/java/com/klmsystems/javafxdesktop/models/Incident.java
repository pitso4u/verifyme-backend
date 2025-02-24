package com.klmsystems.javafxdesktop.models;

import java.sql.Timestamp;

public class Incident {
    private int id;
    private String incidentType; // E.g., Accident, Bullying, Theft, Medical Emergency, etc.
    private String description;
    private Timestamp occurredAt;
    private Timestamp reportedAt;
    private String location;
    private String reportedBy;
    private String involvedParties; // E.g., Students, Staff, Visitors
    private String status; // E.g., Open, In Progress, Resolved
    private String resolution; // Details of the resolution if the incident is resolved
    private boolean followUpNeeded; // Indicates if follow-up actions are required
    private String notes; // Additional remarks or comments related to the incident
    private String createdBy; // Reference to the user who recorded the incident
    private Timestamp createdDate; // Timestamp when the incident was recorded

    // Default constructor
    public Incident() {
    }

    // Parameterized constructor
    public Incident(int id, String incidentType, String description, Timestamp occurredAt, Timestamp reportedAt, String location, String reportedBy, String involvedParties, String status, String resolution, boolean followUpNeeded, String notes, String createdBy, Timestamp createdDate) {
        this.id = id;
        this.incidentType = incidentType;
        this.description = description;
        this.occurredAt = occurredAt;
        this.reportedAt = reportedAt;
        this.location = location;
        this.reportedBy = reportedBy;
        this.involvedParties = involvedParties;
        this.status = status;
        this.resolution = resolution;
        this.followUpNeeded = followUpNeeded;
        this.notes = notes;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(Timestamp occurredAt) {
        this.occurredAt = occurredAt;
    }

    public Timestamp getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(Timestamp reportedAt) {
        this.reportedAt = reportedAt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getInvolvedParties() {
        return involvedParties;
    }

    public void setInvolvedParties(String involvedParties) {
        this.involvedParties = involvedParties;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public boolean isFollowUpNeeded() {
        return followUpNeeded;
    }

    public void setFollowUpNeeded(boolean followUpNeeded) {
        this.followUpNeeded = followUpNeeded;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    // Additional method to print incident information
    public void printIncidentInfo() {
        System.out.println("Incident ID: " + id);
        System.out.println("Incident Type: " + incidentType);
        System.out.println("Description: " + description);
        System.out.println("Occurred At: " + occurredAt);
        System.out.println("Reported At: " + reportedAt);
        System.out.println("Location: " + location);
        System.out.println("Reported By: " + reportedBy);
        System.out.println("Involved Parties: " + involvedParties);
        System.out.println("Status: " + status);
        System.out.println("Resolution: " + resolution);
        System.out.println("Follow-Up Needed: " + followUpNeeded);
        System.out.println("Notes: " + notes);
        System.out.println("Created By: " + createdBy);
        System.out.println("Created Date: " + createdDate);
    }
}