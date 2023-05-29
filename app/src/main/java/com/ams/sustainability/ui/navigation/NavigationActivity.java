package com.ams.sustainability.ui.navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ams.sustainability.R;
import com.ams.sustainability.view.GetStartedCarbonFootprint;
import com.ams.sustainability.ui.adapters.AdapterViewPagerActivity;
import com.ams.sustainability.view.MainLogin;

public class NavigationActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    ViewPager slideViewPager;
    LinearLayout dotIndicator;
    Button btnBack, btnNext, btnSkip;
    TextView[] dots;
    AdapterViewPagerActivity adapterViewPagerActivity;


    ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            setDotIndicator(position);
            if (position > 0) {
                btnBack.setVisibility(View.VISIBLE);
            } else {
                btnBack.setVisibility(View.INVISIBLE);
            }
            if (position == 3) {
                btnSkip.setVisibility(View.INVISIBLE);
                btnNext.setText("Comenzar");
            } else {
                btnSkip.setVisibility(View.VISIBLE);
                btnNext.setText("Siguiente");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        btnBack = findViewById(R.id.backButton);
        btnNext = findViewById(R.id.nextButton);
        btnSkip = findViewById(R.id.skipButton);

        // Obtener instancia de SharedPreferences
        sharedPreferences = getSharedPreferences("MyPreferencesNavigationActivity", MODE_PRIVATE);

        boolean isBtnNextClicked = sharedPreferences.getBoolean("btnNextClicked", false);

        // Verificar el estado del botón
        if (isBtnNextClicked) {
            // Si el botón ya ha sido pulsado, iniciar la nueva actividad directamente
            startActivity(new Intent(this, MainLogin.class));
            finish(); // Finalizar la actividad actual
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItem(0) > 0) {
                    slideViewPager.setCurrentItem(getItem(-1), true);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItem(0) < 3)
                    slideViewPager.setCurrentItem(getItem(1), true);
                else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("btnNextClicked", true);
                    editor.apply();
                    Intent i = new Intent(NavigationActivity.this, GetStartedCarbonFootprint.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("btnNextClicked", true);
                editor.apply();
                Intent i = new Intent(NavigationActivity.this, GetStartedCarbonFootprint.class);
                startActivity(i);
                finish();
            }
        });

        slideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        dotIndicator = (LinearLayout) findViewById(R.id.dotIndicator);
        adapterViewPagerActivity = new AdapterViewPagerActivity(this);
        slideViewPager.setAdapter(adapterViewPagerActivity);
        setDotIndicator(0);
        slideViewPager.addOnPageChangeListener(viewPagerListener);

    }

    public void setDotIndicator(int position) {
        dots = new TextView[4];
        dotIndicator.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226", Html.FROM_HTML_MODE_LEGACY));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.grey, getApplicationContext().getTheme()));
            dotIndicator.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.pastelGreen, getApplicationContext().getTheme()));
    }

    private int getItem(int i) {
        return slideViewPager.getCurrentItem() + i;
    }
}