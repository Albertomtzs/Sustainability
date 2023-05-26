package com.ams.sustainability.ui.navigation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.ams.sustainability.ui.adapters.AdapterViewPagerFragment;
import com.ams.sustainability.ui.fragment.FragmentAccount;
import com.ams.sustainability.R;
import com.ams.sustainability.ui.fragment.FragmentEntries;
import com.ams.sustainability.ui.fragment.FragmentHistorial;
import com.ams.sustainability.ui.fragment.FragmentHome;
import com.ams.sustainability.ui.fragment.FragmentTips;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class NavigationFragment extends AppCompatActivity {

    ViewPager2 viewPager;
    ArrayList<Fragment> fragmentList = new ArrayList<>();

    BottomNavigationView btnNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_fragment);
        viewPager = findViewById(R.id.viewPagerPostLogin);
        btnNav = findViewById(R.id.bottomNavigationView);

        fragmentList.add(new FragmentHome());
        fragmentList.add(new FragmentHistorial());
        fragmentList.add(new FragmentEntries());
        fragmentList.add(new FragmentTips());
        fragmentList.add(new FragmentAccount());

        AdapterViewPagerFragment adapterViewPager = new AdapterViewPagerFragment(this, fragmentList);

        viewPager.setAdapter(adapterViewPager);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        btnNav.setSelectedItemId(R.id.home);
                        break;
                    case 1:
                        btnNav.setSelectedItemId(R.id.historial);
                        break;
                    case 2:
                        btnNav.setSelectedItemId(R.id.entries);
                        break;
                    case 3:
                        btnNav.setSelectedItemId(R.id.tips);
                        break;
                    case 4:
                        btnNav.setSelectedItemId(R.id.account);
                        break;
                }
                super.onPageSelected(position);

            }

        });
        btnNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.historial:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.entries:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.tips:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.account:
                        viewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });

    }
}