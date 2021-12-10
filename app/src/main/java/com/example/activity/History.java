package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TextView;

import com.example.nepse_brokersettlement.HistoryBuySellViewPager;
import com.example.nepse_brokersettlement.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    Toolbar toolbar;
    TextView textView1, textView2;
    TabLayout tabLayout;
    private ViewPager2 viewPager2;
    public static final String[] tabName = {"Buying History","Selling History"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.historytablayout);
        viewPager2 = (ViewPager2) findViewById(R.id.historypageviewer);


        toolbar.setTitle("History");
        toolbar.setSubtitle("See All your Buy/Sell Details");
        setSupportActionBar(toolbar);

        HistoryBuySellViewPager pageViewer = new HistoryBuySellViewPager(this);
        viewPager2.setAdapter(pageViewer);

        new TabLayoutMediator(tabLayout, viewPager2,((tab, position) -> tab.setText(tabName[position]))).attach();



/*
        MySQLClass mySQLClass = new MySQLClass(getApplicationContext());
        List<String> list = new ArrayList<>();
        list = mySQLClass.loadalldata();

 */





    }
}