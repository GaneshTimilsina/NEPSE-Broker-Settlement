package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.nepse_brokersettlement.R;
import com.example.others.AddBuySellPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class BuySellDetails extends AppCompatActivity {
    private ViewPager2 viewPager;
    TabLayout tabLayout;
    androidx.appcompat.widget.Toolbar toolbar;
    public static final String[] titles = {"Buy Details","Sell Details"};
    int pos = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sell_details);

        viewPager = (ViewPager2) findViewById(R.id.viewpagerconetent);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Add Stock Transaction");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String decidePager = intent.getStringExtra("ToBuySellDetailsActivity");

        AddBuySellPageAdapter pageAdapter = new AddBuySellPageAdapter(this,getApplicationContext(),pos);

        viewPager.setAdapter(pageAdapter);
        if (decidePager.equals("FromBuy")){
            Toast.makeText(getApplicationContext(),"Add Details",Toast.LENGTH_SHORT).show();
            viewPager.setCurrentItem(0);

        }
        if (decidePager.equals("FromSell")){
            Toast.makeText(getApplicationContext(),"Provide Details",Toast.LENGTH_SHORT).show();
            viewPager.setCurrentItem(1);
        }

        new TabLayoutMediator(tabLayout, viewPager, ((tab, position) -> tab.setText(titles[position]))).attach();






    }
}