package com.ams.sustainability.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class AdapterViewPagerFragment extends FragmentStateAdapter {

    ArrayList<Fragment> fragment;

    public AdapterViewPagerFragment(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> fragment) {
        super(fragmentActivity);
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragment.get(position);
    }

    @Override
    public int getItemCount() {
        return fragment.size();
    }
}
