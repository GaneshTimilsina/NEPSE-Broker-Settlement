package com.example.nepse_brokersettlement;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fragments.CalculatorBonusAdjustment;
import com.example.fragments.CalculatorBuy;
import com.example.fragments.CalculatorRightShareAdjustment;
import com.example.fragments.CalculatorSell;

public class CalculatorActivityPageViewer extends FragmentStateAdapter {

    public static final String[] TabsName = {"Buy", "Sell", "Bonus Adjustment", "Right Share Adjustment"};
    public CalculatorActivityPageViewer(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position)
    {
        switch (position){
            case 0:
                return new CalculatorBuy();
            case 1:
                return new CalculatorSell();
            case 2:
                return new CalculatorBonusAdjustment();
            case 3:
                return new CalculatorRightShareAdjustment();

        }
        return new CalculatorBuy();
    }

    @Override
    public int getItemCount() {
        return TabsName.length;
    }
}
