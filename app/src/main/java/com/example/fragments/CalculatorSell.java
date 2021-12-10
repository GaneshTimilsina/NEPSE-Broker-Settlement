package com.example.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.class_model.Calculator;
import com.example.nepse_brokersettlement.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculatorSell#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorSell extends Fragment {
    EditText stockBuyingPrice, stockSellingPrice, stockUnits;
    TextView shareAmount, sebonComm, brokerComm, dpFee, capitalGainTax, totalRecAmount, profitLoss, calculate;
    LinearLayout displayLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalculatorSell() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculatorSell.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatorSell newInstance(String param1, String param2) {
        CalculatorSell fragment = new CalculatorSell();
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
        View view = inflater.inflate(R.layout.fragment_calculator_sell, container, false);

        shareAmount = (TextView) view.findViewById(R.id.shareamount);
        sebonComm = (TextView) view.findViewById(R.id.seboncommission);
        brokerComm = (TextView) view.findViewById(R.id.brokercommission);
        dpFee = (TextView) view.findViewById(R.id.dpcharge);
        capitalGainTax = (TextView) view.findViewById(R.id.capitalgain);
        profitLoss = (TextView) view.findViewById(R.id.profitloss);
        totalRecAmount = (TextView) view.findViewById(R.id.receivableamount);
        calculate = (TextView) view.findViewById(R.id.calculateselldetails);

        stockBuyingPrice = (EditText) view.findViewById(R.id.buyprice);
        stockSellingPrice = (EditText) view.findViewById(R.id.sellprice);
        stockUnits = (EditText) view.findViewById(R.id.buyingunits);
        displayLayout = (LinearLayout) view.findViewById(R.id.resultlayout);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayLayout.setVisibility(View.VISIBLE);
                String bPrice = stockBuyingPrice.getText().toString();
                String sPrice = stockSellingPrice.getText().toString();
                String bUnits = stockUnits.getText().toString();
                if (bPrice.trim().length()>0 && sPrice.trim().length()>0 && bUnits.trim().length()>0){
                    int buyingPrice = Integer.parseInt(bPrice);
                    int sellingPrice = Integer.parseInt(sPrice);
                    int buyingUnits = Integer.parseInt(bUnits);

                    Calculator sellingCalculator = new Calculator(buyingPrice, buyingUnits, sellingPrice);
                    double[] getCalculatedData = new double[4];
                    getCalculatedData = sellingCalculator.calculateSell();

                    String WACC = String.format("%.2f",(getCalculatedData[1]/buyingUnits));

                    int total = sellingPrice*buyingUnits;
                    shareAmount.setText("Rs. "+total);
                    sebonComm.setText("Rs. "+String.format("%.2f",(total*0.00015)));
                    brokerComm.setText("Rs. "+String.format("%.2f",getCalculatedData[0]));
                    dpFee.setText("Rs. 25.00");
                    capitalGainTax.setText("Rs. "+String.format("%.2f", getCalculatedData[2]));
                    totalRecAmount.setText("Rs. "+String.format("%.2f", (getCalculatedData[1]-getCalculatedData[2])));

                    if (getCalculatedData[2]<=0){
                        profitLoss.setTextColor(getResources().getColor(R.color.redText));
                        profitLoss.setText(""+getCalculatedData[3]);
                    }
                    else {
                        profitLoss.setTextColor(getResources().getColor(R.color.greenText));
                        profitLoss.setText(String.format("%.2f",getCalculatedData[3]));

                    }

                }
                else {
                    Toast.makeText(getContext().getApplicationContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return view;
    }
}