package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.class_model.Calculator;
import com.example.nepse_brokersettlement.R;

public class ShowBuyingDetails extends AppCompatActivity {
    TextView shareAmount, sebonComm, brokerComm, dpFee, costPerShare, totalAmount;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_buying_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        shareAmount = (TextView) findViewById(R.id.shareamount);
        sebonComm = (TextView) findViewById(R.id.seboncommission);
        brokerComm = (TextView) findViewById(R.id.brokercommission);
        dpFee = (TextView) findViewById(R.id.dpcharge);
        costPerShare = (TextView) findViewById(R.id.costpershare);
        totalAmount = (TextView) findViewById(R.id.totalamount);



        toolbar.setTitle("Buying Amount Details");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        double Price = Double.parseDouble(intent.getStringExtra("Price"));
        double Unites = Double.parseDouble(intent.getStringExtra("Units"));
        int Units = (int) Unites;
        Calculator calculator = new Calculator(Price, Units);
        double[] getCalculatedData = new double[2];
        getCalculatedData = calculator.calculateBuy();
        String WACC = String.format("%.2f",(getCalculatedData[1]/Units));

        double total = Price*Units;
        shareAmount.setText("Rs. "+total);
        sebonComm.setText("Rs. "+String.format("%.2f",(total*0.00015)));
        brokerComm.setText("Rs. "+String.format("%.2f",getCalculatedData[0]));
        dpFee.setText("Rs. 25.00");
        costPerShare.setText("Rs. "+WACC);
        totalAmount.setText("Rs. "+String.format("%.2f", getCalculatedData[1]));


    }
}