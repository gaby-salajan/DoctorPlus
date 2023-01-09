package com.ns.doctorplus.model;

import com.google.firebase.Timestamp;

public class User {
    private String cnp;
    private String name;
    private String birthDate;
    private String tel;
    private String address;

    private String email;
    private String type;

    public User(){
        //need firebase
    }
    public User(String cnp, String fullName, String birthDate, String tel, String address, String email, String type) {
        this.cnp = cnp;
        this.name = fullName;
        this.birthDate = birthDate;
        this.tel = tel;
        this.address = address;

        this.email = email;
        this.type = type;
    }

    public User(String name, String address, String tel, String email,String type) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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
}
