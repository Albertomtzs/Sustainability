package com.ams.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.ams.myapplication.adapter.AdapterViewPager;
import com.ams.myapplication.ui.account.FragmentAccount;
import com.ams.myapplication.ui.home.FragmentHome;
import com.ams.myapplication.ui.social.FragmentSocial;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    ArrayList<Fragment> fragmentList = new ArrayList<>();

    BottomNavigationView btnNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPagerPostLogin);
        btnNav = findViewById(R.id.bottomNavigationView);

        fragmentList.add(new FragmentHome());
        fragmentList.add(new FragmentSocial());
        fragmentList.add(new FragmentAccount());

        AdapterViewPager adapterViewPager = new AdapterViewPager(this, fragmentList);

        viewPager.setAdapter(adapterViewPager);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        btnNav.setSelectedItemId(R.id.home);
                        break;
                    case 1:
                        btnNav.setSelectedItemId(R.id.social);
                        break;
                    case 2:
                        btnNav.setSelectedItemId(R.id.account);

                        break;
                }
                super.onPageSelected(position);

            }

        });
        btnNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.social:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.account:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

    }
}