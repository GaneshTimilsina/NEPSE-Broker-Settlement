package com.example.class_model;

import android.util.Log;
import android.widget.Toast;

public class Calculator {
    double buyingPrice;
    double sellingPrice;
    double quantity;
    private static final double SEBONCOMMISION = 0.00015;
    public static final int DPCHARGE = 25;



    public Calculator(double buyingPrice, int quantity){
        this.buyingPrice = buyingPrice;
        this.quantity =  quantity;
    }
    public Calculator(double buyingPrice, double quantity, double sellingPrice){
        this.buyingPrice = buyingPrice;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
    }
    public double[] calculateBuy()
    {
        double[] payableAmount = new double[2];
        double total = buyingPrice*quantity;
        if (total>=0 && total<=50000){
            payableAmount[0] = total*0.0040;
            payableAmount[1] = total+payableAmount[0]+total*SEBONCOMMISION+DPCHARGE;
        }
        else if (total>50000 && total<=500000){
            payableAmount[0] = total*0.0037;
            payableAmount[1] = total+payableAmount[0]+total*SEBONCOMMISION+DPCHARGE;
        }
        else if (total>500000 && total<=2000000){
            payableAmount[0] = total*0.0034;
            payableAmount[1] = total+payableAmount[0]+total*SEBONCOMMISION+DPCHARGE;
        }
        else if (total>2000000 && total<=10000000){
            payableAmount[0] = total*0.0030;
            payableAmount[1] = total+payableAmount[0]+total*SEBONCOMMISION+DPCHARGE;
        }
        else {
            payableAmount[0] = total*0.0027;
            payableAmount[1] = total+payableAmount[0]+total*SEBONCOMMISION+DPCHARGE;
        }
        return payableAmount;
    }
    public double[] calculateSell(){
        double[] receivalbeAmount = new double[4];
        double total = sellingPrice*quantity;
        if (total>=0 && total<=50000){
            receivalbeAmount[0] = total*0.0040;
            receivalbeAmount[1] = total-receivalbeAmount[0]-total*SEBONCOMMISION-DPCHARGE;
            System.out.println("Price: "+receivalbeAmount[1]);
        }
        else if (total>50000 && total<=500000){
            receivalbeAmount[0] = total*0.0037;
            receivalbeAmount[1] = total-receivalbeAmount[0]-total*SEBONCOMMISION-DPCHARGE;
        }
        else if (total>500000 && total<=2000000){
            receivalbeAmount[0] = total*0.0034;
            receivalbeAmount[1] = total-receivalbeAmount[0]-total*SEBONCOMMISION-DPCHARGE;
        }
        else if (total>2000000 && total<=10000000){
            receivalbeAmount[0] = total*0.0030;
            receivalbeAmount[1] = total-receivalbeAmount[0]-total*SEBONCOMMISION-DPCHARGE;
        }
        else {
            receivalbeAmount[0] = total*0.0027;
            receivalbeAmount[1] = total-receivalbeAmount[0]-total*SEBONCOMMISION-DPCHARGE;
        }

        double[] taxableBuyingAmount = new double[2];
        taxableBuyingAmount = calculateBuy();
        if (receivalbeAmount[1]>taxableBuyingAmount[1]){
            System.out.println("Hello");
            receivalbeAmount[2] = (receivalbeAmount[1]-taxableBuyingAmount[1])*0.075; // Capital Gain tax
            receivalbeAmount[3] = receivalbeAmount[1] - taxableBuyingAmount[1]-receivalbeAmount[2]; //Calculating Profit
        }
        else {
            receivalbeAmount[2]=0;
            receivalbeAmount[3] = taxableBuyingAmount[1] - receivalbeAmount[1];
        }

        return receivalbeAmount;
    }


}
