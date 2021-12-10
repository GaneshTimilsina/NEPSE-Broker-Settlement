package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.class_model.DemoGetterSetterClass;
import com.example.database.MySQLClass;
import com.example.nepse_brokersettlement.R;
import com.example.others.AcountLedgerArrayAdapter;

import java.util.ArrayList;

public class AccountLedger extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    ArrayList<DemoGetterSetterClass> arrayList = new ArrayList<>();
    double[] netAmount = new double[2];
    TextView netText, netAmountText;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_ledger);

        toolbar =(Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.ledgerlistview);
        netText = (TextView) findViewById(R.id.nettextview);
        netAmountText = (TextView) findViewById(R.id.netamount);
        layout = (LinearLayout) findViewById(R.id.totalcalculation);

        toolbar.setTitle("Account Ledger");
        setSupportActionBar(toolbar);


        MySQLClass mySQLClass = new MySQLClass(this);
        arrayList = mySQLClass.loadallledgerdata();

        AcountLedgerArrayAdapter adapter = new AcountLedgerArrayAdapter(this,R.layout.ledgerlistviewdesing,arrayList);
        listView.setAdapter(adapter);

        netAmount = mySQLClass.calculateNetAmount();
        if(netAmount[1]>netAmount[0]){
            Drawable greenBackground = getResources().getDrawable(R.drawable.rectangular_shape_background_green);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                layout.setBackground(greenBackground);
            }
            netText.setText("Receivable Amount");
            netAmountText.setText(String.format("%.2f", netAmount[1]-netAmount[0]));
        }
        else {
            Drawable greenBackground = getResources().getDrawable(R.drawable.rectangular_shape_background_red);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                layout.setBackground(greenBackground);
            }
            netText.setText("Payable Amount");
            netAmountText.setText(String.format("%.2f", netAmount[0]-netAmount[1]));
        }



    }
}