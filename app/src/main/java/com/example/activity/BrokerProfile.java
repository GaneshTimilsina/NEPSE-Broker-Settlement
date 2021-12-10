package com.example.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nepse_brokersettlement.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class BrokerProfile extends AppCompatActivity implements OnMapReadyCallback {
    Toolbar toolbar;
    TextView bNumber, bName, bContact, bAddress, bWebsite, bBranches,internetConnection;
    ImageView call, website;
    GoogleMap maps;
    String BrokerName;
    float lt, lg;
    boolean connected = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broker_profile);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bName = (TextView) findViewById(R.id.brokerName);
        bNumber = (TextView) findViewById(R.id.brokerno);
        bAddress = (TextView) findViewById(R.id.baddress);
        bContact = (TextView) findViewById(R.id.bcontact);
        bBranches = (TextView) findViewById(R.id.bbranch);
        bWebsite = (TextView) findViewById(R.id.website);
        internetConnection = (TextView) findViewById(R.id.internetconnection);
        call = (ImageView) findViewById(R.id.callphone);
        website = (ImageView) findViewById(R.id.wesiteicon);
        //fragment = (Fragment) findViewById(R.id.maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

        boolean isconnect = isConnected();
        if (isconnect == false){
            internetConnection.setVisibility(View.VISIBLE);
        }
        else {
            internetConnection.setVisibility(View.INVISIBLE);
        }

        Intent intent = getIntent();

        bName.setText(intent.getStringExtra("name"));
        bNumber.setText(intent.getStringExtra("no"));
        bAddress.setText(intent.getStringExtra("add"));
        bContact.setText(intent.getStringExtra("ph"));
        bBranches.setText(intent.getStringExtra("branch"));
        bWebsite.setText(intent.getStringExtra("website"));
        BrokerName = intent.getStringExtra("name");

        String Lat, Longi;
        Lat = intent.getStringExtra("lat");
        Longi = intent.getStringExtra("longi");

        lt = Float.parseFloat(Lat);
        lg = Float.parseFloat(Longi);


        toolbar.setTitle("Broker's Profile");
        setSupportActionBar(toolbar);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(intent.getStringExtra("ph").equals("X")){
                    Toast.makeText(getApplicationContext(), "Phone No. Not Available", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + intent.getStringExtra("ph")));
                    startActivity(callIntent);
                }
            }
        });
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(intent.getStringExtra("website").equals("X")){
                    Toast.makeText(getApplicationContext(), "Website Not Available", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent webIntent = new Intent(BrokerProfile.this, WebsiteView.class);
                    webIntent.putExtra("website", intent.getStringExtra("website"));
                    webIntent.putExtra("name", intent.getStringExtra("name"));
                    startActivity(webIntent);
                }
            }
        });




    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        maps = googleMap;
        LatLng brokerAddress = new LatLng(lt, lg);
        maps.addMarker(new MarkerOptions().position(brokerAddress).title(BrokerName));
        maps.moveCamera(CameraUpdateFactory.newLatLngZoom(brokerAddress,12f));


    }
    public boolean isConnected(){

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else {
            connected = false;
        }

        return connected;
    }

}