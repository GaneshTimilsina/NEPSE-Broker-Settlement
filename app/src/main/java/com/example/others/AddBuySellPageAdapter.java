package com.example.others;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fragments.BuyDetailsFragment;
import com.example.fragments.SellDetailsFragment;

public class AddBuySellPageAdapter extends FragmentStateAdapter {
    public static final String[] titles = {"Buy Details","Sell Details"};
    Context context;
    int pos;


    public AddBuySellPageAdapter(@NonNull FragmentActivity fragmentActivity, Context context, int pos) {
        super(fragmentActivity);
        this.context = context;
        this.pos = pos;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //position = pos;
        //Toast.makeText(context.getApplicationContext(),""+position,Toast.LENGTH_SHORT).show();

        switch (position){
            case 0:
                //Toast.makeText(context.getApplicationContext(),"Buy",Toast.LENGTH_SHORT).show();
                return new BuyDetailsFragment();
            case 1:
                //Toast.makeText(context.getApplicationContext(),"Sell",Toast.LENGTH_SHORT).show();
                return new SellDetailsFragment();
        }
        return new BuyDetailsFragment();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}