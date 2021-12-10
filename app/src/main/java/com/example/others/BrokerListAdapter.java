package com.example.others;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activity.BrokerList;
import com.example.activity.BrokerProfile;
import com.example.class_model.BrokerListModel;
import com.example.nepse_brokersettlement.R;

import java.util.ArrayList;

public class BrokerListAdapter extends RecyclerView.Adapter<BrokerListAdapter.ViewHolder> {
    Context context, context1;
    ArrayList<BrokerListModel> arrayList;

    public BrokerListAdapter(@Nullable Context context, ArrayList<BrokerListModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.brokerlistlayout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BrokerListModel model = arrayList.get(position);
        holder.bNumber.setText(model.getBroker_no());
        holder.bName.setText(model.getBroker_Name());
        holder.bContact.setImageResource(model.getPhImage());
        int pos = holder.getAdapterPosition();

        holder.bContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arrayList.get(pos).getPh_no().equals("X")){
                    Toast.makeText(context.getApplicationContext(), "Phone No. Not Available", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + arrayList.get(pos).getPh_no()));
                    context.startActivity(callIntent);
                }
            }

        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context1,BrokerProfile.class);
                intent.putExtra("name",arrayList.get(pos).getBroker_Name());
                intent.putExtra("no",arrayList.get(pos).getBroker_no());
                intent.putExtra("add",arrayList.get(pos).getAddress());
                intent.putExtra("ph",arrayList.get(pos).getPh_no());
                intent.putExtra("branch",arrayList.get(pos).getBranch());
                intent.putExtra("website",arrayList.get(pos).getWebsite());
                intent.putExtra("lat",arrayList.get(pos).getLat());
                intent.putExtra("longi",arrayList.get(pos).getLongi());

                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView bNumber, bName;
        ImageView bContact;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bNumber = (TextView) itemView.findViewById(R.id.bn);
            bName = (TextView) itemView.findViewById(R.id.bname);
            bContact = (ImageView) itemView.findViewById(R.id.bcontact);
            layout = (LinearLayout) itemView.findViewById(R.id.brokerlistlayout);
            context1 = itemView.getContext();


        }
    }
}
