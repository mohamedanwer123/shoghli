package com.example.cm.mywork.Models;

/**
 * Created by cm on 29/04/2018.
 */

public class ClientData {

    String name , job , location , phone;

    public ClientData(){}

    public ClientData(String name, String job, String location, String phone) {
        this.name = name;
        this.job = job;
        this.location = location;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
