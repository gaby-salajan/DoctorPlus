package com.ns.doctorplus.model;

public class Hour {
    private String patient;
    private String selected;
    private String patientName;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Hour(String patient) {
        this.patient = patient;
        selected = "false";
    }
    public Hour(){

    }
    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getselected() {
        return selected;
    }

    public void setselected(String selected) {
        this.selected = selected;
    }
}
