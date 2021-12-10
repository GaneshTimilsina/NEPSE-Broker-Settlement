package com.example.others;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.class_model.DemoGetterSetterClass;
import com.example.nepse_brokersettlement.R;

import java.util.ArrayList;

public class StockInventoryArrayAdapter extends ArrayAdapter {
    Context context;
    ArrayList<DemoGetterSetterClass> arrayList;

    public StockInventoryArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DemoGetterSetterClass> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.inventorylistviewdesing,null);

        TextView id = view.findViewById(R.id.inventid);
        TextView Stock = view.findViewById(R.id.inventstock);
        TextView Price = view.findViewById(R.id.inventprice);
        TextView Units = view.findViewById(R.id.inventunits);


        id.setText(""+(position+1));
        Stock.setText(""+arrayList.get(position).getStock());
        Price.setText(String.format("%.2f",+arrayList.get(position).getPrice()));
        Units.setText(""+arrayList.get(position).getUnits());


        return view;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
