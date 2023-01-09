package com.ns.doctorplus.model;

public class Patient {
    private String name;
    private String address;
    private String tel;
    private String email;
    private String birthDate;


    public Patient(){
        //needed for firebase
    }

    public Patient(String name, String address, String tel, String email, String birthDate) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
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

    public String getbirthDate() {
        return birthDate;
    }

    public void setbirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

}
