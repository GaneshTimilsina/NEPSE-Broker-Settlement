package com.example.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.activity.ShowBuyingDetails;
import com.example.activity.ShowSellingDetails;
import com.example.class_model.DemoGetterSetterClass;
import com.example.database.MySQLClass;
import com.example.nepse_brokersettlement.R;
import com.example.others.BuyHistoryListAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellHistory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellHistory extends Fragment {
    TextView buyingPrice, sellingPrice, units, totalAmount;
    ArrayList<DemoGetterSetterClass> arrayList = new ArrayList<>();
    ListView listView;
    AlertDialog.Builder alertBuilder;
    Context context;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SellHistory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SellHistory.
     */
    // TODO: Rename and change types and number of parameters
    public static SellHistory newInstance(String param1, String param2) {
        SellHistory fragment = new SellHistory();
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
        View view = inflater.inflate(R.layout.fragment_buy_history, container, false);
        buyingPrice = view.findViewById(R.id.textview1);
        sellingPrice = view.findViewById(R.id.textview2);
        units = view.findViewById(R.id.textview3);
        totalAmount = view.findViewById(R.id.textview4);

        buyingPrice.setText("B.Price");
        sellingPrice.setText("s.Price");
        units.setText("Units");
        totalAmount.setText("Total");

        ViewGroup.MarginLayoutParams params1 = (ViewGroup.MarginLayoutParams) buyingPrice.getLayoutParams();
        params1.setMargins(50,0,0,0);

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) sellingPrice.getLayoutParams();
        params.setMargins(50,0,0,0);

        ViewGroup.MarginLayoutParams params2 = (ViewGroup.MarginLayoutParams) units.getLayoutParams();
        params2.setMargins(50,0,0,0);

        ViewGroup.MarginLayoutParams params3 = (ViewGroup.MarginLayoutParams) totalAmount.getLayoutParams();
        params3.setMargins(50,0,0,0);

        listView = view.findViewById(R.id.listviewbuyhistory);
        MySQLClass mySQLClass = new MySQLClass(getContext().getApplicationContext());
        arrayList = mySQLClass.loadallselldata();
        BuyHistoryListAdapter adapter = new BuyHistoryListAdapter(getContext().getApplicationContext(),R.layout.buyingdetailslistviewdesign,arrayList,"FromSellHistory");
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                double buyingPrice = arrayList.get(i).getPrice();
                double units = arrayList.get(i).getTotal_Amount(); // here is reused an adapter and modelclass so quite confusinng for units and selling price
                double sellingPrice = arrayList.get(i).getUnits();

                Intent intent = new Intent(getActivity(), ShowSellingDetails.class);

                intent.putExtra("BPrice",String.valueOf(buyingPrice));
                intent.putExtra("Units",String.valueOf(units));
                intent.putExtra("SPrice",String.valueOf(sellingPrice));
                startActivity(intent);
            }
        });
//        alertBuilder = new AlertDialog.Builder();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                alertBuilder = new AlertDialog.Builder(getActivity());
                alertBuilder.setMessage("")
                        .setCancelable(false)
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel(); //when he click on no perform nothing

                            }
                        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = arrayList.get(pos).getId();
                        mySQLClass.deleteData(id);

                        arrayList.remove(pos);
                        adapter.notifyDataSetChanged();



                    }
                });


                AlertDialog alertDialog = alertBuilder.create(); //creating alter
                alertDialog.setTitle("Do you want to Delete item?");
                alertDialog.show();//showing aleter

                return true;
            }
        });

        return view;
    }
}