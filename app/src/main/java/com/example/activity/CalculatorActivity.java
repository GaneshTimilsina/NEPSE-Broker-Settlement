package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.nepse_brokersettlement.CalculatorActivityPageViewer;
import com.example.nepse_brokersettlement.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CalculatorActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    public static final String[] TabsName = {"Buy", "Sell", "Bonus Adjustment", "Right Share Adjustment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.calculatortablayout);
        viewPager2 = (ViewPager2) findViewById(R.id.calculatorviewpager);

        toolbar.setTitle("Calculator");
        setSupportActionBar(toolbar);

        CalculatorActivityPageViewer pageViewer = new CalculatorActivityPageViewer(this);
        viewPager2.setAdapter(pageViewer);

        new TabLayoutMediator(tabLayout, viewPager2,((tab, position) -> tab.setText(TabsName[position]))).attach();
    }
}