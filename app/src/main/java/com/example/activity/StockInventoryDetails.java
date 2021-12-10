package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;

import com.example.class_model.DemoGetterSetterClass;
import com.example.database.MySQLClass;
import com.example.nepse_brokersettlement.R;
import com.example.others.StockInventoryArrayAdapter;

import java.util.ArrayList;

public class StockInventoryDetails extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    ArrayList<DemoGetterSetterClass> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_inventory_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.inventorylistview);



        toolbar.setTitle("Stock Inventory");
        setSupportActionBar(toolbar);

        MySQLClass mySQLClass = new MySQLClass(this);
        arrayList = mySQLClass.readInventoryTable();
        //Toast.makeText(getApplicationContext(),arrayList.get(0).getId(),Toast.LENGTH_SHORT).show();
        StockInventoryArrayAdapter adapter = new StockInventoryArrayAdapter(getApplicationContext(),R.layout.inventorylistviewdesing,arrayList);
        listView.setAdapter(adapter);



    }
}