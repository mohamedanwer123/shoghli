package com.example.cm.mywork.Models;

/**
 * Created by cm on 02/05/2018.
 */

public class ProductData {

    String img , name , price , company , describtion;

    public ProductData(){}
    public ProductData(String img, String name, String price, String company, String describtion) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.company = company;
        this.describtion = describtion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }
}
