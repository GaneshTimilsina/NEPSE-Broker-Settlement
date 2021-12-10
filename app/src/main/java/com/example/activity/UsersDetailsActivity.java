package com.example.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.class_model.BrokerListModel;
import com.example.nepse_brokersettlement.MainActivity;
import com.example.nepse_brokersettlement.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class UsersDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    Toolbar toolbar;
    SharedPreferences sharedPreferences;

    public static final String FILENAME = "MyData";
    public static final String MYNAME = "UserName";
    public static final String MYPH = "UserPh";
    public static final String BROKERNO = "BrokerNo";
    public static final String EMAIL = "Email";
    public static final String BROKERNAME = "BrokerName";
    public static final String BROKERADDRESS = "BrokerAdd";
    public static final String BROKERPH = "BrokerPH";
    public static final String BROKERWEBSITE = "BrokerWeb";
    public static final String BROKERLAT = "BrokerLat";
    public static final String BROKERLOG = "BrokerLog";
    String BrokerName;
    String MyName;
    String Myph;
    String MyEmail;
    String BrokerNo;

    EditText name, ph, email, brokerno;
    TextView saveData;

    TextView usern, userph, usereml, brokername, brokernom, brokeradd, brokerph, brokerweb;
    LinearLayout add, view;

    float lt;
    float lngg;

    GoogleMap maps;
    BrokerList brokerList = new BrokerList();
    String[] bno = brokerList.broker_no;
    String[] bnm = brokerList.broker_name;
    String[] badd = brokerList.address;
    String[] bph = brokerList.ph_no;
    String[] web = brokerList.website;
    String[] lat = brokerList.latiitude;
    String[] lg = brokerList.longitude;
    final int[] index = {0};


    ArrayList<BrokerListModel> arrayList = new ArrayList<>();
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_details);

        //BrokerListModel brokerListModel = new BrokerListModel(BrokerNO);
        ArrayList arrayList = new ArrayList();
//        BrokerList brokerList = new BrokerList();
        //brokerList.onCreate();
//        String[] bno = brokerList.broker_no;
//        String[] bnm = brokerList.broker_name;
//        String[] badd = brokerList.address;
//        String[] bph = brokerList.ph_no;
//        String[] web = brokerList.website;
//        String[] lat = brokerList.latiitude;
//        String[] lg = brokerList.longitude;
//        final int[] index = {0};

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        saveData = (TextView) findViewById(R.id.addtransactionbuy);
        name = (EditText) findViewById(R.id.userName);
        ph = (EditText) findViewById(R.id.userPh);
        email = (EditText) findViewById(R.id.userEmail);
        brokerno = (EditText) findViewById(R.id.userBn);

        usern = (TextView) findViewById(R.id.UserShowName);
        userph = (TextView) findViewById(R.id.UserShowPh);
        usereml = (TextView) findViewById(R.id.UserShowEmail);
        brokername = (TextView) findViewById(R.id.BrokerShowName);
        brokernom = (TextView) findViewById(R.id.brokernomb);
        brokeradd = (TextView) findViewById(R.id.baddress);
        brokerph = (TextView) findViewById(R.id.bcontact);
        brokerweb = (TextView) findViewById(R.id.website);

        add = (LinearLayout) findViewById(R.id.dataAddLayout);
        view = (LinearLayout) findViewById(R.id.alldetails);


        toolbar.setTitle("User's Data");
        toolbar.inflateMenu(R.menu.toolbareditbutton);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences(FILENAME, Context.MODE_APPEND);
        System.out.println("MyName: " + sharedPreferences.getString(MYNAME, ""));
        if (sharedPreferences.getString(MYNAME, "").length() > 0) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
            mapFragment.getMapAsync(this);


            add.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);

            MyName = sharedPreferences.getString(MYNAME,"");
            Myph = sharedPreferences.getString(MYPH,"");
            MyEmail = sharedPreferences.getString(EMAIL,"");

            BrokerNo = sharedPreferences.getString(BROKERNO,"");
            BrokerName = sharedPreferences.getString(BROKERNAME,"");
            String BrokerAdd= sharedPreferences.getString(BROKERADDRESS,"");
            String BrokerCont= sharedPreferences.getString(BROKERPH,"");
            String BrokerWeb= sharedPreferences.getString(BROKERWEBSITE,"");

            String BrokerLot= sharedPreferences.getString(BROKERLAT,"");
            String BrokerLong= sharedPreferences.getString(BROKERLOG,"");

            usern.setText(MyName);
            userph.setText(Myph);
            usereml.setText(MyEmail);
            brokername.setText(BrokerName);
            brokernom.setText(BrokerNo);
            brokeradd.setText(BrokerAdd);
            brokerph.setText(BrokerCont);
            brokerweb.setText(BrokerWeb);

            lt = Float.parseFloat(BrokerLot);
            lngg = Float.parseFloat(BrokerLong);

        } else
        {
            view.setVisibility(View.INVISIBLE);
              add.setVisibility(View.VISIBLE);


        //Operated if there is no data Available or Edit is  necessary
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSharedPreferencedData();

            }
        });
    }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        maps = googleMap;
        LatLng brokerAddress = new LatLng(lt, lngg);
        maps.addMarker(new MarkerOptions().position(brokerAddress).title(BrokerName));
        maps.moveCamera(CameraUpdateFactory.newLatLngZoom(brokerAddress,12f));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbareditbutton,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.editbutton){
            add.setVisibility(View.VISIBLE);
            view.setVisibility(View.INVISIBLE);

            name.setText(MyName);
            ph.setText(Myph);
            email.setText(MyEmail);
            brokerno.setText(BrokerNo);
            saveData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addSharedPreferencedData();
                }
            });




        }

        return super.onOptionsItemSelected(item);
    }
    public void addSharedPreferencedData(){
        //Toast.makeText(getApplicationContext(),"Data Saved Successfully!",Toast.LENGTH_SHORT).show();
        String Name = name.getText().toString().trim();
        String Phone = ph.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String BrokerNO = brokerno.getText().toString().trim();


        if (Name.length() > 0 && Phone.length() > 0 && Email.length() > 0 && BrokerNO.length() > 0) {
            if (Phone.length() == 10) {
                if (Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    for (int ind = 0; ind < bno.length; ind++) {
                        if (bno[ind].equals(BrokerNO)) {
                            index[0] = ind;
                            break;
                        }

                    }
                    if (index[0] != 0) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(MYNAME, Name);
                        editor.putString(MYPH, Phone);
                        editor.putString(EMAIL, Email);
                        editor.putString(BROKERNO, BrokerNO);
                        editor.putString(BROKERNAME, bnm[index[0]]);
                        editor.putString(BROKERPH, bph[index[0]]);
                        editor.putString(BROKERADDRESS, badd[index[0]]);
                        editor.putString(BROKERWEBSITE, web[index[0]]);
                        editor.putString(BROKERLAT, lat[index[0]]);
                        editor.putString(BROKERLOG, lg[index[0]]);
                        editor.commit();

                        Toast.makeText(getApplicationContext(),"Data Saved Successfully!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UsersDetailsActivity.this, MainActivity.class));


                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Broker Number", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Please Provied Correct email Address", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Please Provide Correct Ph number", Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(getApplicationContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        }

    }

}