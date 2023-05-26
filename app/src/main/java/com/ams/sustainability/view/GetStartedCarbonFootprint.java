package com.ams.sustainability.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.ams.sustainability.R;

public class GetStartedCarbonFootprint extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_carbon_footprint);

        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GetStartedCarbonFootprint.this, MainLogin.class);
                startActivity(i);
                finish();
            }
        });
    }
}