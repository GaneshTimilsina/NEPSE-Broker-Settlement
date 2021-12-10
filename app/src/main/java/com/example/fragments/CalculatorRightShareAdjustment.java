package com.example.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nepse_brokersettlement.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculatorRightShareAdjustment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorRightShareAdjustment extends Fragment {
    EditText priceBeforeBookClose, rightPercentage;
    TextView calcuateAdjustedPrice, adjustedPrice, textRightName;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalculatorRightShareAdjustment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculatorRightShareAdjustment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatorRightShareAdjustment newInstance(String param1, String param2) {
        CalculatorRightShareAdjustment fragment = new CalculatorRightShareAdjustment();
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
        View view =  inflater.inflate(R.layout.fragment_calculator_bonus_adjustment, container, false);
        priceBeforeBookClose = (EditText) view.findViewById(R.id.pricebeforeadjustment);
        rightPercentage = (EditText) view.findViewById(R.id.bonuspercentage);
        calcuateAdjustedPrice = (TextView) view.findViewById(R.id.caculatebonusadjustment);
        adjustedPrice = (TextView) view.findViewById(R.id.priceafteradjustment);
        textRightName = (TextView) view.findViewById(R.id.percentage);

        textRightName.setText("Right Share Percentage");
        calcuateAdjustedPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sPrice = priceBeforeBookClose.getText().toString();
                String sRight = rightPercentage.getText().toString();

                if (sPrice.trim().length()>0 && sRight.trim().length()>0){
                    float marketPrice = Float.parseFloat(sPrice);
                    float rightPercentages = Float.parseFloat(sRight);


                    float price = (marketPrice+rightPercentages)/(1+(rightPercentages/100));


                    InputMethodManager im = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(rightPercentage.getWindowToken(), 0);
                    adjustedPrice.setVisibility(View.VISIBLE);
                    adjustedPrice.setText("Adjusted Price: Rs. "+String.format("%.2f",price));

                }
                else {
                    Toast.makeText(getContext().getApplicationContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
}