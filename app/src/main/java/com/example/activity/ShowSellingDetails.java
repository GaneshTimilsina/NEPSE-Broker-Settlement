package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.class_model.Calculator;
import com.example.nepse_brokersettlement.R;

public class ShowSellingDetails extends AppCompatActivity {
    Toolbar toolbar;
    TextView shareAmount, sebonComm, brokerComm, dpFee, capitalGainTax, totalRecAmount, profitLoss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_selling_details);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        shareAmount = (TextView) findViewById(R.id.shareamount);
        sebonComm = (TextView) findViewById(R.id.seboncommission);
        brokerComm = (TextView) findViewById(R.id.brokercommission);
        dpFee = (TextView) findViewById(R.id.dpcharge);
        capitalGainTax = (TextView) findViewById(R.id.capitalgain);
        profitLoss = (TextView) findViewById(R.id.profitloss);
        totalRecAmount = (TextView) findViewById(R.id.receivableamount);



        toolbar.setTitle("Selling Details");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String bPrice = intent.getStringExtra("BPrice");
        String sPrice = intent.getStringExtra("SPrice");
        String bUnits = intent.getStringExtra("Units");

        double bp = Double.parseDouble(bPrice);
        double sp = Double.parseDouble(sPrice);
        double unt = Double.parseDouble(bUnits);

        Calculator sellingCalculator = new Calculator(bp, unt, sp);
        double[] getCalculatedData = new double[4];
        getCalculatedData = sellingCalculator.calculateSell();

        String WACC = String.format("%.2f",(getCalculatedData[1]/unt));

        double total = sp*unt;
        shareAmount.setText("Rs. "+total);
        sebonComm.setText("Rs. "+String.format("%.2f",(total*0.00015)));
        brokerComm.setText("Rs. "+String.format("%.2f",getCalculatedData[0]));
        dpFee.setText("Rs. 25.00");
        capitalGainTax.setText("Rs. "+String.format("%.2f", getCalculatedData[2]));
        totalRecAmount.setText("Rs. "+String.format("%.2f", (getCalculatedData[1]-getCalculatedData[2])));

        if (getCalculatedData[2]<=0){
            profitLoss.setTextColor(getResources().getColor(R.color.redText));
            profitLoss.setText(""+getCalculatedData[3]);
        }
        else {
            profitLoss.setTextColor(getResources().getColor(R.color.greenText));
            profitLoss.setText(String.format("%.2f",getCalculatedData[3]));

        }




    }
}