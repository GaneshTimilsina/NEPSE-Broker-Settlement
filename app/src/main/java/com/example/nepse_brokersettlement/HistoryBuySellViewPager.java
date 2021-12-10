package com.example.nepse_brokersettlement;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fragments.BuyHistory;
import com.example.fragments.SellHistory;

public class HistoryBuySellViewPager extends FragmentStateAdapter {
    public static final String[] tabName = {"Buying History","Selling History"};

    public HistoryBuySellViewPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new BuyHistory();
            case 1:
                return new SellHistory();

        }

        return new BuyHistory();
    }

    @Override
    public int getItemCount() {
        return tabName.length;
    }
}
