package com.example.nepse_brokersettlement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.ContentInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activity.BuySellDetails;
import com.example.activity.CalculatorActivity;
import com.example.activity.CashPaidActivity;
import com.example.activity.ListMenu;
import com.example.activity.UsersDetailsActivity;
import com.example.database.MySQLClass;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView time, date, marketdetails, netText, netAmountText,colonText;
    private Button HomeButton;
    private FloatingActionButton floatingMenuButton;
    private ExtendedFloatingActionButton extendedBuy, extendedSell, extendedCalculator, extendedCashPaid;
    private ImageView listButton, UserBtn;
    boolean showMenu = true;
    double[] netAmount = new double[2];
    MySQLClass mySQLClass = new MySQLClass(this);
    LinearLayout layout;

    SharedPreferences sharedPreferences;
    public static final String FILENAME = "MyData";
    public static final String MYNAME = "UserName";


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //See the user data available or not!
        sharedPreferences = getSharedPreferences(FILENAME, Context.MODE_APPEND);
        String UserName = sharedPreferences.getString(MYNAME,"");

        if (UserName.equals("")){
            startActivity(new Intent(MainActivity.this, UsersDetailsActivity.class));
            Toast.makeText(this,"Please Provide Your data",Toast.LENGTH_SHORT).show();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("NEPSE");
        toolbar.setSubtitle("Broker Settlement");
        setSupportActionBar(toolbar);

        time = (TextView) findViewById(R.id.realtime);
        date = (TextView) findViewById(R.id.realdate);
        marketdetails = (TextView) findViewById(R.id.marketdetails);
        floatingMenuButton = (FloatingActionButton) findViewById(R.id.addfloatingbtn);
        listButton = (ImageView) findViewById(R.id.listBtn);
        UserBtn = (ImageView) findViewById(R.id.userBtn);
        extendedBuy = (ExtendedFloatingActionButton) findViewById(R.id.floatingextbuy);
        extendedSell = (ExtendedFloatingActionButton) findViewById(R.id.floatingextsell);
        extendedCalculator = (ExtendedFloatingActionButton) findViewById(R.id.floatingextcalculator);
        extendedCashPaid = (ExtendedFloatingActionButton) findViewById(R.id.floatingextcashpaid);
        netText = (TextView) findViewById(R.id.nettextview);
        netAmountText = (TextView) findViewById(R.id.netamount);
        colonText = (TextView) findViewById(R.id.colontext);

        if (ActivityCompat.checkSelfPermission( MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
                }


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate localDate = LocalDate.now();
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalTime localTime = LocalTime.now();
        }

        Thread thread = null;
        Runnable runnable = new DateTimeCounter();
        thread = new Thread(runnable);
        thread.start();
        notificationAtMarketOpen();


        quickAccountReview();
        //Floating and ExtendedFloating Button function
        floatingMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showMenu==true){
                    showFloatingMenu();
                }
                else {
                    hideFloatingMenu();
                }

            }
        });

        extendedBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BuySellDetails.class);
                intent.putExtra("ToBuySellDetailsActivity","FromBuy");
                startActivity(intent);
                hideFloatingMenu();
            }
        });
        extendedSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BuySellDetails.class);
                intent.putExtra("ToBuySellDetailsActivity","FromSell");
                startActivity(intent);
                hideFloatingMenu();
            }
        });
        extendedCashPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CashPaidActivity.class));
                hideFloatingMenu();
            }
        });

        extendedCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
                hideFloatingMenu();
            }
        });


        //Bottom Layout Function
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListMenu.class));
            }
        });

        UserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UsersDetailsActivity.class));
            }
        });
        //End of Bottom Layout Function


    }



    public void showFloatingMenu(){
        showMenu = false;
        extendedBuy.setVisibility(View.VISIBLE);
        extendedSell.setVisibility(View.VISIBLE);
        extendedCashPaid.setVisibility(View.VISIBLE);
        extendedCalculator.setVisibility(View.VISIBLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            floatingMenuButton.animate().rotation(90);
            extendedBuy.animate().translationY(-getResources().getDimension(R.dimen.SetBuy));
            extendedSell.animate().translationY(-getResources().getDimension(R.dimen.SetSell));
            extendedCashPaid.animate().translationY(-getResources().getDimension(R.dimen.SetCash));
            extendedCalculator.animate().translationY(-getResources().getDimension(R.dimen.SetCalculator));
        }



    }
    public void hideFloatingMenu(){
        showMenu = true;
        extendedBuy.setVisibility(View.INVISIBLE);
        extendedSell.setVisibility(View.INVISIBLE);
        extendedCashPaid.setVisibility(View.INVISIBLE);
        extendedCalculator.setVisibility(View.INVISIBLE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            extendedCalculator.animate().translationY(0);
            floatingMenuButton.animate().rotation(0);
            extendedBuy.animate().translationY(0);
            extendedSell.animate().translationY(0);
            extendedCashPaid.animate().translationY(0);
        }

    }

       private void notificationAtMarketOpen() {
        String currentDate = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("h:mm:ss a",Locale.getDefault()).format(new Date());
        String currentDays = new SimpleDateFormat("EEE",Locale.getDefault()).format(new Date());
        String currentTime24hrs = new SimpleDateFormat("kk",Locale.getDefault()).format(new Date());


        Integer currenttime24 = Integer.parseInt(currentTime24hrs);
        if (currentDays.equals("Fri") || currentDays.equals("Sat")){
            marketdetails.setText("Market Closed");
        }
        else {

        }

    }


    public void dowork() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {

                    String currentDate = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault()).format(new Date());
                    String currentTime = new SimpleDateFormat("h:mm:ss a", Locale.getDefault()).format(new Date());
                    String currentDays = new SimpleDateFormat("EEE", Locale.getDefault()).format(new Date());
                    String currentTime24hrs = new SimpleDateFormat("kk", Locale.getDefault()).format(new Date());

                    date.setText(currentDate);
                    time.setText(currentTime);

                    Integer currenttime24 = Integer.parseInt(currentTime24hrs);
                    if (currentDays.equals("Fri") || currentDays.equals("Sat")) {
                        marketdetails.setText("Market Closed");
                    } else {
                        if (currenttime24 >= 11 && currenttime24 <= 14) {
                            Drawable greenBackground = getResources().getDrawable(R.drawable.rectangular_shape_background_green);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                marketdetails.setBackground(greenBackground);
                            }
                            marketdetails.setText("Market Open");

                        } else {
                            marketdetails.setText("Market Closed");
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class DateTimeCounter implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    dowork();
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aboutus:
                Toast.makeText(getApplicationContext(), "About Us", Toast.LENGTH_LONG).show();
                break;
            case R.id.userprofile:
                Toast.makeText(getApplicationContext(), "User Profile", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, UsersDetailsActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    public void quickAccountReview(){
        netAmount = mySQLClass.calculateNetAmount();
        if(netAmount[1]>netAmount[0]){
            netText.setTextColor(getResources().getColor(R.color.greenText));
            netAmountText.setTextColor(getResources().getColor(R.color.greenText));
            colonText.setTextColor(getResources().getColor(R.color.greenText));
            netText.setText("Receivable Amount");
            netAmountText.setText(String.format("%.2f", netAmount[1]-netAmount[0]));
        }
        else if (netAmount[1]<netAmount[0]){
            netText.setTextColor(getResources().getColor(R.color.redText));
            netAmountText.setTextColor(getResources().getColor(R.color.redText));
            colonText.setTextColor(getResources().getColor(R.color.redText));
            netText.setText("Payable Amount");
            netAmountText.setText(String.format("%.2f", netAmount[0]-netAmount[1]));
        }
        else {
            netText.setTextColor(getResources().getColor(R.color.black));
            netAmountText.setTextColor(getResources().getColor(R.color.black));
            colonText.setVisibility(View.INVISIBLE);
            netText.setText("Payable Amount");
            netAmountText.setText("Amount Settled");

        }

    }
}