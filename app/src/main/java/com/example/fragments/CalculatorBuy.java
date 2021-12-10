package com.example.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.class_model.Calculator;
import com.example.nepse_brokersettlement.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculatorBuy#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorBuy extends Fragment {
    EditText stockPrice, stockUnits;
    TextView shareAmount, sebonComm, brokerComm, dpFee, costPerShare, totalAmount, calculate;
    LinearLayout displayLayout;
    private String sName, sPrice, sUnits;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalculatorBuy() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculatorBuy.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatorBuy newInstance(String param1, String param2) {
        CalculatorBuy fragment = new CalculatorBuy();
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
        View view = inflater.inflate(R.layout.fragment_calculator_buy, container, false);

        shareAmount = (TextView) view.findViewById(R.id.shareamount);
        sebonComm = (TextView) view.findViewById(R.id.seboncommission);
        brokerComm = (TextView) view.findViewById(R.id.brokercommission);
        dpFee = (TextView) view.findViewById(R.id.dpcharge);
        costPerShare = (TextView) view.findViewById(R.id.costpershare);
        totalAmount = (TextView) view.findViewById(R.id.totalamount);
        calculate = (TextView) view.findViewById(R.id.calculatebuydetails);

        stockPrice = (EditText) view.findViewById(R.id.buyingprice);
        stockUnits = (EditText) view.findViewById(R.id.buyingunits);
        displayLayout = (LinearLayout) view.findViewById(R.id.resultlayout);




        calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sPrice = stockPrice.getText().toString();
                sUnits = stockUnits.getText().toString();
                if (sPrice.trim().length()>0 && sUnits.trim().length()>0){
                    displayLayout.setVisibility(View.VISIBLE);
                    double price,total;
                    int units;
                    price = Integer.parseInt(sPrice);
                    units = Integer.parseInt(sUnits);


                    Calculator calculator = new Calculator(price, units);
                    double[] getCalculatedData = new double[2];
                    getCalculatedData = calculator.calculateBuy();

                    String WACC = String.format("%.2f",(getCalculatedData[1]/units));

                    total = price*units;
                    shareAmount.setText("Rs. "+total);
                    sebonComm.setText("Rs. "+String.format("%.2f",(total*0.00015)));
                    brokerComm.setText("Rs. "+String.format("%.2f",getCalculatedData[0]));
                    dpFee.setText("Rs. 25.00");
                    costPerShare.setText("Rs. "+WACC);
                    totalAmount.setText("Rs. "+String.format("%.2f", getCalculatedData[1]));

                    InputMethodManager im = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(stockPrice.getWindowToken(),0);

                }
                else {
                    Toast.makeText(getContext().getApplicationContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }
}