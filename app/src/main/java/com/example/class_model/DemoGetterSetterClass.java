package com.example.class_model;

import androidx.annotation.BinderThread;

public class DemoGetterSetterClass {
    int id;
    double Price, Total_Amount,Units;
    double WACC;
    String Stock, BuySell;

    public DemoGetterSetterClass(int id, double price, double units, double WACC, double total_Amount, String stock, String buySell) {
        this.id = id;
        this.Price = price;
        this.Units = units;
        this.WACC = WACC;
        this.Total_Amount = total_Amount;
        this.Stock = stock;
        this.BuySell = buySell;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        this.Price = price;
    }

    public double getUnits() {
        return Units;
    }

    public void setUnits(double units) {
        this.Units = units;
    }

    public double getWACC() {
        return WACC;
    }

    public void setWACC(double WACC) {
        this.WACC = WACC;
    }

    public double getTotal_Amount() {
        return Total_Amount;
    }

    public void setTotal_Amount(int total_Amount) {
        this.Total_Amount = total_Amount;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public String getBuySell() {
        return BuySell;
    }

    public void setBuySell(String buySell) {
        this.BuySell = buySell;
    }
}
