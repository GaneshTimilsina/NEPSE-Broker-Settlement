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

public class BuyHistoryListAdapter extends ArrayAdapter<DemoGetterSetterClass> {
    Context context;
    ArrayList<DemoGetterSetterClass> arrayList = new ArrayList<>();
    String fromWhere;

    public BuyHistoryListAdapter(Context context, int textViewResouceId, ArrayList<DemoGetterSetterClass> objects, String decision){
        super(context,textViewResouceId,objects);
        this.context = context;
        this.fromWhere = decision;
        arrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.buyingdetailslistviewdesign,null);

        TextView id = (TextView) view.findViewById(R.id.buyhistid);
        TextView stock = (TextView) view.findViewById(R.id.buyhiststock);
        TextView price = (TextView) view.findViewById(R.id.buyhistprice);
        TextView units = (TextView) view.findViewById(R.id.buyhistunits);
        TextView total = (TextView) view.findViewById(R.id.buyhisttotal);
        TextView wacc = (TextView) view.findViewById(R.id.buyhistwacc);
        TextView rs = (TextView) view.findViewById(R.id.rstextview);

        if (fromWhere.equals("FromSellHistory")){
            ViewGroup.MarginLayoutParams rsLayout = (ViewGroup.MarginLayoutParams) rs.getLayoutParams();
            rsLayout.setMargins(30,0,0,0);


            ViewGroup.MarginLayoutParams sellingpriceLayout = (ViewGroup.MarginLayoutParams) units.getLayoutParams();
            sellingpriceLayout.setMargins(20,0,0,0);

            ViewGroup.MarginLayoutParams unitsLayout = (ViewGroup.MarginLayoutParams) total.getLayoutParams();
            unitsLayout.setMargins(55,0,0,0);
            total.getLayoutParams().width = 130;

            ViewGroup.MarginLayoutParams waccLayout = (ViewGroup.MarginLayoutParams) wacc.getLayoutParams();
            waccLayout.setMargins(30,0,0,0);

            id.setText(""+(position+1));
            stock.setText(""+arrayList.get(position).getStock());
            price.setText(""+arrayList.get(position).getPrice());
            total.setText(""+arrayList.get(position).getTotal_Amount());
            wacc.setText(""+String.format("%.2f", arrayList.get(position).getWACC()));
        }

        id.setText(""+(position+1));
        stock.setText(""+arrayList.get(position).getStock());
        price.setText(""+arrayList.get(position).getPrice());
        units.setText("Rs."+arrayList.get(position).getUnits());
        total.setText(""+arrayList.get(position).getTotal_Amount());
        wacc.setText(""+String.format("%.2f", arrayList.get(position).getWACC()));

        return view;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
