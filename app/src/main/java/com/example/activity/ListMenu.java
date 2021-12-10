package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.class_model.ListMenuGetterSetter;
import com.example.nepse_brokersettlement.R;
import com.example.others.ListMenuCustomAdapter;

import java.util.ArrayList;

public class ListMenu extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    ArrayList<ListMenuGetterSetter> arrayList = new ArrayList<>();

    public static final String[] titles = {"Buy", "Sell","History","Calculator","Stock Inventory","Account Ledger","Broker List"};
    public static final String[] subtitles = {"Provide your Buying Details","Provide your Selling Details",
            "Your Buy and Sell History","Calculator Related to Stock Market","Find Your Inventory",
            "Find your Debit and Credit History here", "Find details of all broker that provide service in Nepal"};
    public static final int[] images = {R.drawable.buy, R.drawable.selllist,R.drawable.history,R.drawable.calculator,
            R.drawable.inventory,R.drawable.ledgerbook, R.drawable.brokerlist};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.listview);


        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);
        for (int i = 0; i<titles.length; i++) {
            arrayList.add(new ListMenuGetterSetter(titles[i], subtitles[i], images[i]));
        }

        ListMenuCustomAdapter listMenuCustomAdapter = new ListMenuCustomAdapter(this,R.layout.listmenudesing,arrayList);
        listView.setAdapter(listMenuCustomAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0: {
                        Intent intent = new Intent(ListMenu.this, BuySellDetails.class);
                        intent.putExtra("ToBuySellDetailsActivity", "FromBuy");
                        startActivity(intent);
                        break;
                    }
                    case 1: {
                        Intent intent1 = new Intent(ListMenu.this, BuySellDetails.class);
                        intent1.putExtra("ToBuySellDetailsActivity", "FromSell");
                        startActivity(intent1);
                        break;
                    }
                    case 2:
                        startActivity(new Intent(ListMenu.this, History.class));
                        Toast.makeText(getApplicationContext(),"Buying & Selling History",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        startActivity(new Intent(ListMenu.this, CalculatorActivity.class));
                        Toast.makeText(getApplicationContext(),"Calculator",Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        startActivity(new Intent(ListMenu.this, StockInventoryDetails.class));
                        Toast.makeText(getApplicationContext(),"Stock Inventory Click",Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        startActivity(new Intent(ListMenu.this, AccountLedger.class));
                        Toast.makeText(getApplicationContext(),"Account Ledger",Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        startActivity(new Intent(ListMenu.this, BrokerList.class));
                        Toast.makeText(getApplicationContext(),"Broker List",Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
    }
}