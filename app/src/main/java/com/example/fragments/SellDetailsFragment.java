package com.example.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.class_model.Calculator;
import com.example.database.MySQLClass;
import com.example.nepse_brokersettlement.MainActivity;
import com.example.nepse_brokersettlement.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellDetailsFragment extends Fragment {
    EditText stockName, buyingPrice, sellingPrice, stockUnits;
    TextView addTransaction;
    public double[] price = new double[2];

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SellDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SellDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SellDetailsFragment newInstance(String param1, String param2) {
        SellDetailsFragment fragment = new SellDetailsFragment();
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
        View view = inflater.inflate(R.layout.fragment_sell_details, container, false);

        stockName = (EditText) view.findViewById(R.id.stockname);
        buyingPrice = (EditText) view.findViewById(R.id.buyingprice);
        sellingPrice = (EditText) view.findViewById(R.id.sellingprice);
        stockUnits = (EditText) view.findViewById(R.id.buyingunits);
        addTransaction = (TextView) view.findViewById(R.id.addtransactionbuy);
        buyingPrice.setFocusable(false);
        sellingPrice.setFocusable(false);
        stockUnits.setFocusable(false);

        stockName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MySQLClass mySQLClass = new MySQLClass(getContext().getApplicationContext());
                String Name = String.valueOf(charSequence);
                price = mySQLClass.findStockAvaialbeOrNot(Name);
                if (price[0]!=0){
                    System.out.println("Display: "+price[0]+price[1]);
                    buyingPrice.setText(String.format("%.2f",price[0]));
                    buyingPrice.setTypeface(buyingPrice.getTypeface(), Typeface.NORMAL);
                    buyingPrice.setTextColor(getResources().getColor(R.color.white));
                    buyingPrice.setTextSize(20);
                    buyingPrice.setFocusableInTouchMode(true);
                    sellingPrice.setFocusableInTouchMode(true);
                    stockUnits.setFocusableInTouchMode(true);

                }
                else {
                    //buyingPrice.setTypeface(buyingPrice.getTypeface(), Typeface.ITALIC);
                    buyingPrice.setText("You haven't buy this STOCK yet!");
                    buyingPrice.setTextColor(getResources().getColor(R.color.redText));
                    buyingPrice.setTextSize(15);
                    buyingPrice.setFocusable(false);
                    sellingPrice.setFocusable(false);
                    stockUnits.setFocusable(false);
                }
                //System.out.println("Id: "+ id);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sName, sBuyingPrice, sSellingPrice, sUnits;

                sName = stockName.getText().toString();
                sBuyingPrice = buyingPrice.getText().toString();
                sSellingPrice = sellingPrice.getText().toString();
                sUnits = stockUnits.getText().toString();



                if (sName.trim().length()>0 && sBuyingPrice.trim().length()>0 &&  sSellingPrice.trim().length()>0 && sUnits.trim().length()>0 && price[0]!=0) {
                    double buyingprice, sellingprice;
                    int units;
                    buyingprice = Double.parseDouble(sBuyingPrice);
                    sellingprice = Double.parseDouble(sSellingPrice);
                    units = Integer.parseInt(sUnits);
                    if (units <= price[1]) {
                        double[] getCalculatedData = new double[4];

                        Calculator calculator = new Calculator(buyingprice, units, sellingprice);
                        getCalculatedData = calculator.calculateSell();
                        MySQLClass mySQLClass = new MySQLClass(getContext().getApplicationContext());
                        mySQLClass.sellStockDetails(sName,buyingprice,sellingprice,units,getCalculatedData[3],(getCalculatedData[1]-getCalculatedData[2]));
                        Toast.makeText(getContext().getApplicationContext(), "Profit: "+getCalculatedData[3]+"\nReceivable: "+(getCalculatedData[1]-getCalculatedData[2]), Toast.LENGTH_SHORT).show();
                        mySQLClass.updateInventoryOnSellStock(sName,units);
                        mySQLClass.insertBuyIntoLedger(sName,(getCalculatedData[1]-getCalculatedData[2]),"Selling Details");
                        startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
                    }
                    else {
                        Toast.makeText(getContext().getApplicationContext(), "Insufficient Stock!\nYou have only "+price[1] + "Stocks", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getContext().getApplicationContext(), "Value Not Added", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return view;
    }
}