package com.example.others;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.class_model.ListMenuGetterSetter;
import com.example.nepse_brokersettlement.R;

import java.util.ArrayList;

public class ListMenuCustomAdapter extends ArrayAdapter<ListMenuGetterSetter> {
    Context context;
    ArrayList<ListMenuGetterSetter> arrayList;

    public ListMenuCustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ListMenuGetterSetter> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = objects;
    }
    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.listmenudesing,null);
        TextView textViewTitle = (TextView) view.findViewById(R.id.listviewtitle);
        TextView textViewSubTitle = (TextView) view.findViewById(R.id.listviewsubtitle);
        ImageView Image = (ImageView) view.findViewById(R.id.listviewimage);

        textViewTitle.setText(arrayList.get(position).getTitle());
        textViewSubTitle.setText(arrayList.get(position).getSubtitles());
        Image.setImageResource(arrayList.get(position).getImage());




        return view;
    }
}
