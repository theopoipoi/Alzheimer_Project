package com.example.theopoipoi.alzheimer_project;

public class User {
    //Create a user with the same attributes as the table in the DB
    //This is the informations of the user
    private String name;
    private String firstname;
    private String phone;
    private String address;
    private int password;

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
