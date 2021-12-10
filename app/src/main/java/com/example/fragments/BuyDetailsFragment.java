package com.example.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.class_model.Calculator;
import com.example.database.MySQLClass;
import com.example.nepse_brokersettlement.MainActivity;
import com.example.nepse_brokersettlement.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyDetailsFragment extends Fragment {

    EditText stockName, stockPrice, stockUnits;
    TextView addTransaction;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BuyDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuyDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuyDetailsFragment newInstance(String param1, String param2) {
        BuyDetailsFragment fragment = new BuyDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_buy_details, container, false);

        stockName = (EditText) view.findViewById(R.id.stockname);
        stockPrice = (EditText) view.findViewById(R.id.buyingprice);
        stockUnits = (EditText) view.findViewById(R.id.buyingunits);
        addTransaction = (TextView) view.findViewById(R.id.addtransactionbuy);

        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sName = stockName.getText().toString();
                String sPrice = stockPrice.getText().toString();
                String sUnits = stockUnits.getText().toString();

                MySQLClass mySQLClass = new MySQLClass(getContext().getApplicationContext());


                if (sName.trim().length()>0 && sPrice.trim().length()>0 && sUnits.trim().length()>0){
                    double price = Double.parseDouble(sPrice);
                    int units = Integer.parseInt(sUnits);
                    Calculator calculator = new Calculator(price,units);
                    double[] getCalculatedData = new double[2];
                    double[] details = new double[2];
                    getCalculatedData = calculator.calculateBuy();
                    double WACC = getCalculatedData[1]/units;

                    details = mySQLClass.findStockAvaialbeOrNot(sName);
                    //System.out.println("Stock Found: "+details[0]+details[1]);
                    if (details[0]>0){
                        double new_price = ((price*units + details[0]*details[1])/(units+details[1]));
                        System.out.println("New Price"+new_price);
                        double new_units = units+details[1];
                        mySQLClass.updateAddedStock(sName,new_price, new_units);
                        mySQLClass.insertBuyIntoLedger(sName, getCalculatedData[1], "Buying Details");
                        mySQLClass.addStockDetails(sName, price, units, WACC, getCalculatedData[1]);

                    }
                    else {

                        mySQLClass.addStockDetails(sName, price, units, WACC, getCalculatedData[1]);
                        mySQLClass.insertintoInventory(sName, price, units);
                        mySQLClass.insertBuyIntoLedger(sName, getCalculatedData[1], "Buying Details");
                        System.out.println(sName + " " + sPrice + " " + getCalculatedData + " " + WACC);
                    }

                    InputMethodManager im = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(stockName.getWindowToken(),0);
                    startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
                }
                else {
                    Toast.makeText(getContext().getApplicationContext(),"Please Fill All Field",Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
}