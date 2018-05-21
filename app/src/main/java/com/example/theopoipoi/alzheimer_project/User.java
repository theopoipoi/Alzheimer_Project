package com.example.theopoipoi.alzheimer_project;

public class User {
    //Create a user with the same attributes as the table in the DB
    //This is the informations of the user
    private String username;
    private int password;
    private int battery_min;
    private int battery_max;
    private int temperature_max;
    private int temperature_min;
    private int humidity_min;
    private int humidity_max;
    private String phone;

    public int getBattery_min() {
        return battery_min;
    }

    public int getBattery_max() {
        return battery_max;
    }

    public int getTemperature_max() {
        return temperature_max;
    }

    public int getTemperature_min() {
        return temperature_min;
    }

    public int getHumidity_min() {
        return humidity_min;
    }

    public int getHumidity_max() {
        return humidity_max;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public int getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setBattery_min(int battery_min) {
        this.battery_min = battery_min;
    }

    public void setBattery_max(int battery_max) {
        this.battery_max = battery_max;
    }

    public void setTemperature_max(int temperature_max) {
        this.temperature_max = temperature_max;
    }

    public void setTemperature_min(int temperature_min) {
        this.temperature_min = temperature_min;
    }

    public void setHumidity_min(int humidity_min) {
        this.humidity_min = humidity_min;
    }

    public void setHumidity_max(int humidity_max) {
        this.humidity_max = humidity_max;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
