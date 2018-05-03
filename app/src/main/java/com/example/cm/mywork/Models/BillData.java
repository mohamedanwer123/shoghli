package com.example.cm.mywork.Models;

/**
 * Created by cm on 30/04/2018.
 */

public class BillData {

    String date , name , amount , publicPrice , discount , totalPrice_beforeDiscount , totalPrice_afterDiscount;

    public BillData(){}

    public BillData(String date, String name, String amount, String publicPrice, String discount, String totalPrice_beforeDiscount, String totalPrice_afterDiscount) {
        this.date = date;
        this.name = name;
        this.amount = amount;
        this.publicPrice = publicPrice;
        this.discount = discount;
        this.totalPrice_beforeDiscount = totalPrice_beforeDiscount;
        this.totalPrice_afterDiscount = totalPrice_afterDiscount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPublicPrice() {
        return publicPrice;
    }

    public void setPublicPrice(String publicPrice) {
        this.publicPrice = publicPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotalPrice_beforeDiscount() {
        return totalPrice_beforeDiscount;
    }

    public void setTotalPrice_beforeDiscount(String totalPrice_beforeDiscount) {
        this.totalPrice_beforeDiscount = totalPrice_beforeDiscount;
    }

    public String getTotalPrice_afterDiscount() {
        return totalPrice_afterDiscount;
    }

    public void setTotalPrice_afterDiscount(String totalPrice_afterDiscount) {
        this.totalPrice_afterDiscount = totalPrice_afterDiscount;
    }
}
