package com.example.cm.mywork.Models;

/**
 * Created by cm on 27/04/2018.
 */

public class UserData {

    String name , password , phone , job , company , image;

    public UserData(){}

    public UserData(String name, String password, String phone, String job, String company , String image) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.job = job;
        this.company = company;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
