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

public class AcountLedgerArrayAdapter extends ArrayAdapter {
    ArrayList<DemoGetterSetterClass> arrayList = new ArrayList<>();
    Context context;

    public AcountLedgerArrayAdapter(@NonNull Context context, int resource, ArrayList<DemoGetterSetterClass> object) {
        super(context, resource, object);
        this.context = context;
        this.arrayList = object;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.ledgerlistviewdesing,null);

        TextView id = view.findViewById(R.id.ledgerid);
        TextView stock = view.findViewById(R.id.ledgerstock);
        TextView debit = view.findViewById(R.id.ledgerdebit);
        TextView credit = view.findViewById(R.id.ledgercredit);
        TextView remark = view.findViewById(R.id.ledgerremark);

        id.setText(""+(position+1));
        stock.setText(arrayList.get(position).getStock());
        debit.setText(String.format("%.2f", arrayList.get(position).getPrice()));
        credit.setText(String.format("%.2f", arrayList.get(position).getWACC()));
        remark.setText(arrayList.get(position).getBuySell());


        return view;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
