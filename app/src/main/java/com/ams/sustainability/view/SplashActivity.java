package com.ams.sustainability.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.ams.sustainability.R;
import com.ams.sustainability.ui.NavigationActivity;

public class MainActivity extends AppCompatActivity {

    public static int SPLASH_TIMER = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMER);
    }
}