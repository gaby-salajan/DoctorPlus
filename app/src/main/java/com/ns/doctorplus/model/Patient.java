package com.ns.doctorplus.model;

import com.google.firebase.Timestamp;

public class Patient {
    private String cnp;
    private String name;
    private Timestamp birthDate;
    private String tel;
    private String address;

    private String email;


    public Patient(){
        //needed for firebase
    }

    public Patient(String cnp, String fullName, Timestamp birthDate, String tel, String address, String email) {
        this.cnp = cnp;
        this.name = fullName;
        this.birthDate = birthDate;
        this.tel = tel;
        this.address = address;

        this.email = email;

    }

    public Patient(String name, String address, String tel, String email, Timestamp birthDate) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
