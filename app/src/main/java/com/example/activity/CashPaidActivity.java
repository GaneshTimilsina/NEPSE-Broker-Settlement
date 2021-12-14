package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.MySQLClass;
import com.example.nepse_brokersettlement.MainActivity;
import com.example.nepse_brokersettlement.R;

public class CashPaidActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText amount, remark, stock;
    TextView addTransaction;
    Toolbar toolbar;
    MySQLClass mySQLClass = new MySQLClass(this);
    Spinner spinner,city_spinner;
    public static final String[] array = {"Choose one", "Cash Received", "Cash Paid"};
    String selected=null;
    int dec = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_paid);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        amount = (EditText) findViewById(R.id.paidamount);
        remark = (EditText) findViewById(R.id.paidremark);
        stock = (EditText) findViewById(R.id.paidstock);
        addTransaction = (TextView) findViewById(R.id.addtransactionbuy);
        spinner = (Spinner) findViewById(R.id.spinner1);

        toolbar.setTitle("Save Paid Cash");
        setSupportActionBar(toolbar);

        //spinner.setOnItemSelectedListener(this);


        ArrayAdapter aa = new ArrayAdapter(getApplicationContext(),R.layout.spinner_list,array);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        spinner.setOnItemSelectedListener(this);



        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String paidAmount = amount.getText().toString();
                String paidRemark = remark.getText().toString();
                String paidStock = stock.getText().toString();
                if(paidAmount.trim().length()>0 && paidRemark.trim().length()>0 && paidStock.trim().length()>0 && dec==1){
                    double cash = Double.parseDouble(paidAmount);

                        mySQLClass.insertBuyIntoLedger(paidStock,cash,selected);
                        startActivity(new Intent(CashPaidActivity.this, MainActivity.class));
                        Toast.makeText(getApplicationContext(),selected + " Successfully",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(),"Enter Valid Data",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selected = adapterView.getSelectedItem().toString();
        if(selected.equals("Choose one")){
            dec = 0;
        }
        else {
            dec=1;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        dec=0;
    }
}