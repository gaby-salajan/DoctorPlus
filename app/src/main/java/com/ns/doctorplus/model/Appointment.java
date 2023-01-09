package com.ns.doctorplus.model;

import java.util.Date;

public class Appointment {
    private int number;
    private Date date;
    private Date dateTaken;
    private String doctorName;

    public Appointment(int number, Date date, Date dateTaken, String doctorName) {
        this.number = number;
        this.date = date;
        this.dateTaken = dateTaken;
        this.doctorName = doctorName;
    }

    public int getAppointmentNo() {
        return number;
    }

    public void setAppointmentNo(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Date dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


    public void printAppointment(){
        System.out.println("Programarea "+ getAppointmentNo()+" e in curs de procesare");
    }
}
